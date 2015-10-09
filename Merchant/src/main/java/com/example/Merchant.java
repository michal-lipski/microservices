package com.example;


import java.math.BigDecimal;

public class Merchant {

    public String id;

    public String name;

    public BigDecimal percentage;

    public BigDecimal minAmount;

    public BigDecimal maxPayback;

    public Merchant(String id, String name, BigDecimal percentage, BigDecimal minAmount, BigDecimal maxPayback) {
        this.id = id;
        this.name = name;
        this.percentage = percentage;
        this.minAmount = minAmount;
        this.maxPayback = maxPayback;
    }
}
