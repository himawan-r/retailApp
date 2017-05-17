package com.mitrais.retail.retailApp.dao;

import com.mitrais.retail.retailApp.model.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Himawan_R on 5/17/2017.
 */
public interface ProductRepository extends CrudRepository<Product, String> {

}
