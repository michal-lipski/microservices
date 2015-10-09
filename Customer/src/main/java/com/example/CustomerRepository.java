package com.example;

import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.Optional.ofNullable;

@Component
public class CustomerRepository {
    private Map<String,Customer> customers =new HashMap<>();

    public CustomerRepository() {
        customers.put("1", new Customer("1","John Doe","123"));
    }

    public void store(Customer customer) {

        this.customers.put(customer.id, customer);
    }

    public Optional<Customer> load(String id) {
        return ofNullable(customers.get(id));
    }

    public Optional<Customer> loadWithCreditCard(String creditCard) {
        return customers.values().stream().filter(customer -> customer.creditCard.equals(creditCard)).findFirst();
    }

    public Collection<Customer> findAll() {
        return customers.values();
    }
}
