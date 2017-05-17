package com.mitrais.retail.retailApp.dao;

import com.mitrais.retail.retailApp.model.Bill;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Himawan_R on 5/17/2017.
 */
public interface BillRepository extends CrudRepository<Bill, String> {

}