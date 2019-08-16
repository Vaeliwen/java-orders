package com.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long ordnum;

    private double ordamount;
    private double advanceamount;
    private String orddescription;

    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
    @JsonIgnoreProperties("orders")
    private Customers customer;

    public Orders(double ordamount, double advanceamount,  Customers customer, String orddescription){
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.orddescription = orddescription;
        this.customer = customer;
    }

    public Orders(){
    }

    //Getters
    public long getOrdnum(){
        return ordnum;
    }
    public double getOrdamount(){
        return ordamount;
    }
    public double getAdvanceamount(){
        return advanceamount;
    }
    public String getOrddescription(){
        return orddescription;
    }
    public Customers getCustomer(){
        return customer;
    }

    //Setters
    public void setOrdnum(long ordnum){
        this.ordnum = ordnum;
    }
    public void setOrdamount(double ordamount){
        this.ordamount = ordamount;
    }
    public void setAdvanceamount(double advanceamount){
        this.advanceamount = advanceamount;
    }
    public void setOrddescription(String orddescription){
        this.orddescription = orddescription;
    }
    public void setCustomer(Customers customer){
        this.customer = customer;
    }


}
