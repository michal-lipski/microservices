package com.example;


import java.math.BigDecimal;

public class Payback {

    public String id;

    public String customerId;

    public BigDecimal amount;

    public String creditCardNumber;

    public  String merchantId;

    public Payback(String id, String customerId, BigDecimal amount, String creditCardNumber, String merchantId) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.creditCardNumber = creditCardNumber;
        this.merchantId = merchantId;
    }
}
