package com.mitrais.retail.retailApp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Himawan_R on 5/17/2017.
 */
@Entity
@Table(name = "bill")
public class Bill extends CommonEntity implements Serializable {

    @Id
    private String     id;
    @Column(name = "totalAmount")
    private Double     totalAmount;
    @Column(name = "totalDiscount")
    private Double     totalDiscount;
    @Column(name = "userid")
    private String     userId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill", cascade = CascadeType.ALL)
    public List<BillItem> billItem;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    public User user;

    public Bill() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public List<BillItem> getBillItem() {
        return billItem;
    }

    public void setBillItem(List<BillItem> billItem) {
        this.billItem = billItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
