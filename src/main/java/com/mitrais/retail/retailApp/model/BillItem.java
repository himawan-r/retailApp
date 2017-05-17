package com.mitrais.retail.retailApp.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Himawan_R on 5/17/2017.
 */
@Entity
@Table(name = "billitem")
public class BillItem extends CommonEntity implements Serializable {

    @Id
    private String id;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "discount")
    private Integer quantity;
    @Column(name = "type")
    private Integer type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Bill bill;


    public BillItem() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}