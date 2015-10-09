package com.example;

import com.example.integration.CustomerClient;
import com.example.integration.PaybackCustomer;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.logging.Logger;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class PaybackController {

    private PaybackRepository paybackRepository;
    private DiscoveryClient discoveryClient;
    private RestTemplate restTemplate;
    private CustomerClient customerClient;
    private PaybackService paybackService;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PaybackController.class);


    @Autowired
    public PaybackController(PaybackRepository paybackRepository, DiscoveryClient discoveryClient, RestTemplate restTemplate,
                             CustomerClient customerClient, PaybackService paybackService) {
        this.paybackRepository = paybackRepository;
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
        this.customerClient = customerClient;
        this.paybackService = paybackService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<Payback> findAll() {
        return paybackRepository.findAll();
    }

    //    testy roznych sposobow pobierania uslug
    @RequestMapping(value = "/payback/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Object findOne(@PathVariable Long id) {
        //jeden sposób
        Application application = discoveryClient.getApplication("customer");
        //z tego kożna coś wyciągnac
        //drugi sposób
        Object object = restTemplate.getForObject("http://customer/1", Object.class);
        //fein client
        ResponseEntity<PaybackCustomer> one = customerClient.findOne("1");

        return one;
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping(value = "/payback", method = POST)
    public ResponseEntity<Void> register(@RequestBody Purchase purchase) {
        logger.error("register");
        Payback result = paybackService.registerPaybackFor(purchase);
        return created(UriComponentsBuilder.fromPath("/paybacks/{customerId}")
                .buildAndExpand(result.customerId).toUri()).build();
    }


    public ResponseEntity<Void> fallback(Purchase purchase) {
        logger.error("fallback");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
