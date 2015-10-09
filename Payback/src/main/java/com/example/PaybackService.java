package com.example;

import com.example.integration.CustomerClient;
import com.example.integration.MerchantClient;
import com.example.integration.PaybackCustomer;
import com.example.integration.PaybackMerchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class PaybackService {

    private CustomerClient customerClient;
    private MerchantClient merchantClient;
    private PaybackRepository paybackRepository;

    @Autowired
    public PaybackService(CustomerClient customerClient,MerchantClient merchantClient,PaybackRepository paybackRepository) {
        this.customerClient = customerClient;
        this.merchantClient = merchantClient;
        this.paybackRepository = paybackRepository;
    }

    Payback registerPaybackFor(Purchase purchase) {
        PaybackCustomer customer = customerClient.findByCreditCard(purchase.creditCardNumber).getBody();

        PaybackMerchant merchant = merchantClient.findById(purchase.merchantId).getBody();

        BigDecimal amount = merchant.paybackFor(purchase);

        String id = UUID.randomUUID().toString();
        return paybackRepository.store(new Payback(id, customer.id, amount, purchase.creditCardNumber, merchant.id));
    }
}
