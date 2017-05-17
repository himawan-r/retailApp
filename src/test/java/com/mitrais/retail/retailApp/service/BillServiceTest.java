package com.mitrais.retail.retailApp.service;

import com.mitrais.retail.retailApp.dao.UserRepository;
import com.mitrais.retail.retailApp.model.Bill;
import com.mitrais.retail.retailApp.model.BillItem;
import com.mitrais.retail.retailApp.model.Employee;
import com.mitrais.retail.retailApp.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by Himawan_R on 5/17/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BillServiceTest {

    @Mock
    private UserRepository userDao;

    @InjectMocks
    private BillServiceImpl billService;


    private Bill createScenarioBill01(){
        Bill bill = new Bill();
        List<BillItem> billItemList = new ArrayList<>();
        BillItem billItem1 = new BillItem();
        BillItem billItem2 = new BillItem();

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

    @Test
    //user is not customer, user is not affiliate, user is not employee
    //discount calc static = (300/100) *5 = 15,
    public void test_01_a_save() {
        Bill bill = createScenarioBill01();
        bill = billService.calculateDiscount(bill);

        assertEquals(285, bill.getTotalAmount() , 0);
        assertEquals(15, bill.getTotalDiscount() , 0);
    }

    @Test
    //user is an employee
    //discount calc 30% x 300 = 90 + static = (300/100) *5 = 15, total discount 105, total amount 195
    public void test_01_b_save() {
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

}