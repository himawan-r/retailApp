package com.mitrais.retail.retailApp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "affiliate")
public class Affiliate extends CommonEntity implements Serializable {

    @Id
    private String     id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    public User user;

    public Affiliate() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}