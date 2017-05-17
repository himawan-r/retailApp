package com.mitrais.retail.retailApp.model;

import com.mitrais.retail.retailApp.model.CommonEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product extends CommonEntity implements Serializable {

    @Id
    private String     id;
    @Column(name = "productname")
    private String     productName;
    @Column(name = "price")
    private Integer     price;

    public Product() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }



}