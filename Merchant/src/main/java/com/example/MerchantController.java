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
public class MerchantController {

    private MerchantRepository merchantRepository;


    @Autowired
    public MerchantController(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<Merchant> findAll() {
        return merchantRepository.findAll();
    }

    @RequestMapping(value = "/merchants/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Merchant> findById(@PathVariable("id") String id) {
        return merchantRepository.load(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity("not found", HttpStatus.NOT_FOUND));
    }



}
