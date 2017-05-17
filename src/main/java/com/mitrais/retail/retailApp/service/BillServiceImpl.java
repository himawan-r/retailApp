package com.mitrais.retail.retailApp.service;

import com.mitrais.retail.retailApp.dao.BillRepository;
import com.mitrais.retail.retailApp.dao.UserRepository;
import com.mitrais.retail.retailApp.model.Bill;
import com.mitrais.retail.retailApp.model.BillItem;
import com.mitrais.retail.retailApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

/**
 * Created by Himawan_R on 5/17/2017.
 */
public class BillServiceImpl implements BillService {
    @Autowired
    BillRepository billDao;

    @Autowired
    UserRepository userDao;

    @Override
    public void createCompleteBill(Bill bill){
        calculateDiscount(bill);
        billDao.save(bill);
    }

    @Override
    public List<Bill> findAllBill(){
        List<Bill> bills = new ArrayList<>();
        billDao.findAll().forEach(bill -> bills.add(bill));
        return bills;
    }

    private Bill calculateDiscount(Bill bill){
        List<BillItem> billItems = bill.getBillItem();
        List<BillItem> others = new ArrayList<>();
        User user = userDao.findOne(bill.getUserId());
        Integer discount = 0;

        //separate groceries assuming 1 = groceries and other
        billItems.forEach(billItem -> {if(billItem.getType() != 1) others.add(billItem);});

        //count the discount, rule 6 user only get one percentage based discounts. But if all condition met we use the highest amount
        if(user.getEmployee() != null) {
            discount =  30;
        } else if(user.getAffiliate() != null){
            discount = 15;
        } else if(user.getCustomer() != null && getDiffYears(user.getCustomer().getCreatedDate(), new Date()) > 2) {
            discount = 5;
        }

        //count the 6 requirement rule, where the percentage discount only applied to non groceries that stored in others
        Double percentageDiscount = 0.00, staticDiscount= 0.00, totalDiscount= 0.00;
        Double totalOthersAmount  = 0.00, totalAmount = 0.00;
        for(int itemCount = 0; itemCount< others.size(); itemCount++){
            totalOthersAmount = totalOthersAmount + (others.get(itemCount).getQuantity() * others.get(itemCount).getAmount());
        }
        percentageDiscount = (totalOthersAmount * discount) / 100;

        //count discount for the 4 requirement rule which applies to all item which stored on billItems (not sure if this rule applied after reducing the total amount with the percentage discount first)
        for(int itemCount = 0; itemCount< billItems.size(); itemCount++){
            totalAmount = totalAmount + (billItems.get(itemCount).getQuantity() * billItems.get(itemCount).getAmount());
        }
        staticDiscount = Math.floor(totalAmount / 100);
        totalDiscount = percentageDiscount + staticDiscount;

        bill.setTotalAmount(totalAmount-totalDiscount);
        bill.setTotalDiscount(totalAmount);
        return bill;
    }

    //i took calc function from stackoverflow
    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
