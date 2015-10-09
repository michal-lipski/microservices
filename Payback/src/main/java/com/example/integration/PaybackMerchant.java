package com.example.integration;


import com.example.Purchase;

import java.math.BigDecimal;
import java.util.Optional;

import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL32;

public class PaybackMerchant {

    public String id;
    public BigDecimal percentage;
    public BigDecimal minAmount;
    public BigDecimal maxPayback;

    public BigDecimal paybackFor(Purchase purchase) {
        return Optional.of(purchase)
                .filter ( it -> percentage != null && it.amount != null )
        .filter(it -> minAmount == null || minAmount.compareTo(it.amount) <= 0)
        .map(it -> percentage.multiply(it.amount, DECIMAL32))
        .map(it -> maxPayback != null && maxPayback.compareTo(it) < 0 ? maxPayback : it)
        .orElse(ZERO);
    }
}
