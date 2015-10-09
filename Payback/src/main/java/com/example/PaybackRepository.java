package com.example;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Optional.ofNullable;

@Component
public class PaybackRepository {

    private Map<String,Payback> paybacks =new HashMap<>();

    public PaybackRepository() {
    }

    public Payback store(Payback payback) {
        this.paybacks.put(payback.id, payback);
        return payback;
    }

    public Optional<Payback> load(String id) {
        return ofNullable(paybacks.get(id));
    }

    public Collection<Payback> findAll() {
        return paybacks.values();
    }
}
