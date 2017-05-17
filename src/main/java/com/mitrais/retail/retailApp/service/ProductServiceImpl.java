package com.mitrais.retail.retailApp.service;

import com.mitrais.retail.retailApp.dao.ProductRepository;
import com.mitrais.retail.retailApp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Himawan_R on 5/17/2017.
 */
@Repository
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productDao;

    @Override
    public List<Product> findAllProducts(){
        List<Product> products = new ArrayList<>();
        productDao.findAll().forEach(product -> products.add(product));
        return products;
    }
}
