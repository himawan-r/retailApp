package com.mitrais.retail.retailApp.service;

import com.mitrais.retail.retailApp.model.Bill;

import java.util.List;

/**
 * Created by Himawan_R on 5/17/2017.
 */
public interface BillService {
    void createCompleteBill(Bill bill);
    List<Bill> findAllBill();
    Bill calculateDiscount(Bill bill);
}
