package com.mitrais.retail.retailApp.service;

import com.mitrais.retail.retailApp.dao.BillRepository;
import com.mitrais.retail.retailApp.dao.UserRepository;
import com.mitrais.retail.retailApp.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by Himawan_R on 5/17/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BillServiceTest {

    @Mock
    private UserRepository userDao;

    @Mock
    private BillRepository billDao;

    @InjectMocks
    private BillServiceImpl billService;


    private Bill createScenarioBill01(){
        Bill bill = new Bill();
        List<BillItem> billItemList = new ArrayList<>();
        BillItem billItem1 = new BillItem();
        BillItem billItem2 = new BillItem();

        bill.setCreatedDate(new Date());
        bill.setUpdatedDate(new Date());

        //bill item 1 is not groceries
        billItem1.setAmount(100.00);
        billItem1.setQuantity(2);
        billItem1.setType(2);
        billItemList.add(billItem1);

        //bill item 2 is not groceries
        billItem2.setAmount(50.00);
        billItem2.setQuantity(2);
        billItem2.setType(2);
        billItemList.add(billItem2);

        bill.setBillItem(billItemList);
        return bill;
    }

    private User createUser(){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        return user;
    }

    private Employee createEmployee(){
        Employee employee = new Employee();
        employee.setId(UUID.randomUUID().toString());
        return employee;
    }

    private Affiliate createAffiliate(){
        Affiliate affiliate = new Affiliate();
        affiliate.setId(UUID.randomUUID().toString());
        return affiliate;
    }

    private Customer createCustomer(){
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        Calendar date = Calendar.getInstance();
        date.add(Calendar.YEAR, -3);
        customer.setCreatedDate(date.getTime());
        return customer;
    }

    @Test
    //user is not customer, user is not affiliate, user is not employee
    //discount calc static = (300/100) *5 = 15,
    public void test_01_a_calculatebill() {
        Bill bill = createScenarioBill01();
        bill = billService.calculateDiscount(bill);

        assertEquals(285, bill.getTotalAmount() , 0);
        assertEquals(15, bill.getTotalDiscount() , 0);
    }

    @Test
    //user is an employee
    //discount calc 30% x 300 = 90 + static = (300/100) *5 = 15, total discount 105, total amount 195
    public void test_01_b_calculatebill() {
        Bill bill = createScenarioBill01();
        User user = createUser();
        Employee empl = createEmployee();
        user.setEmployee(empl);
        bill.setUser(user);

        when(userDao.findOne(anyString())).thenReturn(user);
        bill = billService.calculateDiscount(bill);

        assertEquals(195, bill.getTotalAmount() , 0);
        assertEquals(105, bill.getTotalDiscount() , 0);
    }


    @Test
    //user is an affiliate
    //discount calc 15% x 300 = 45 + static = (300/100) *5 = 15, total discount 60, total amount 240
    public void test_01_c_calculatebill() {
        Bill bill = createScenarioBill01();
        User user = createUser();
        Affiliate affiliate = createAffiliate();
        user.setAffiliate(affiliate);
        bill.setUser(user);

        when(userDao.findOne(anyString())).thenReturn(user);
        bill = billService.calculateDiscount(bill);

        assertEquals(240, bill.getTotalAmount() , 0);
        assertEquals(60, bill.getTotalDiscount() , 0);
    }

    @Test
    //user is an customer
    //discount calc 5% x 300 = 15 + static = (300/100) *5 = 15, total discount 30, total amount 270
    public void test_01_d_calculatebill() {
        Bill bill = createScenarioBill01();
        User user = createUser();
        Customer customer = createCustomer();
        user.setCustomer(customer);
        bill.setUser(user);

        when(userDao.findOne(anyString())).thenReturn(user);
        bill = billService.calculateDiscount(bill);

        assertEquals(270, bill.getTotalAmount() , 0);
        assertEquals(30, bill.getTotalDiscount() , 0);
    }

    @Test
    //user is an customer
    //discount calc 5% x 300 = 15 + static = (300/100) *5 = 15, total discount 30, total amount 270
    //this time check the savebill with previous data
    public void test_02_savebill() {
        Bill bill = createScenarioBill01();
        User user = createUser();
        Customer customer = createCustomer();
        user.setCustomer(customer);
        bill.setUser(user);
        bill.setCreatedBy(user.getId());
        bill.setUpdatedBy(user.getId());

        when(userDao.findOne(anyString())).thenReturn(user);
        when(billDao.save(any(Bill.class))).then(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {

                return null;
            }
        });

        billService.createCompleteBill(bill);

        verify(userDao, times(1)).findOne(anyString());
        verify(billDao, times(1)).save(any(Bill.class));
    }

}