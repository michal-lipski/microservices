package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private CustomerRepository customerRepository;
    private CounterService myCounter;
    private BeanToRefresh beanToRefresh;


    @Autowired
    public CustomerController(CustomerRepository customerRepository,CounterService myCounter,BeanToRefresh beanToRefresh) {
        this.customerRepository = customerRepository;
        this.myCounter = myCounter;
        this.beanToRefresh = beanToRefresh;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<Customer> findAll() {
        return customerRepository.findAll();
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Customer> findOne(@PathVariable String id) {
        return customerRepository.load(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity("not found", HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/customer/{creditCard}")
    public ResponseEntity<Customer> findByCreditCard(@PathVariable String creditCard) throws InterruptedException {
        Optional<Customer> customer = customerRepository.loadWithCreditCard(creditCard);
        ResponseEntity<Customer> customerResponseEntity = customer
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity("not found", HttpStatus.NOT_FOUND));
        myCounter.increment("counter.customer");
        return customerResponseEntity;
    }

    @RequestMapping(value = "/prop", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String prop(){
        return "response:"+beanToRefresh.someProperty;
    }

}
