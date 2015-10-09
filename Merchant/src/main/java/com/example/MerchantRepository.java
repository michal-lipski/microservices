package com.example;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Optional.ofNullable;

@Component
public class MerchantRepository {

    public MerchantRepository() {
        merchants.put("1",new Merchant("1","John", BigDecimal.ONE,BigDecimal.TEN,BigDecimal.ONE));
    }

    private Map<String,Merchant> merchants =new HashMap<>();

    public void store(Merchant merchant) {
        this.merchants.put(merchant.id, merchant);
    }

    public Optional<Merchant> load(String id) {
        return ofNullable(merchants.get(id));
    }

    public Collection<Merchant> findAll() {
        return merchants.values();
    }
}
