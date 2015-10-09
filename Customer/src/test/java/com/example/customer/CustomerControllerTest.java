package com.example.customer;

import com.example.Customer;
import com.example.CustomerApplication;
import com.example.CustomerController;
import com.example.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CustomerApplication.class)
public class CustomerControllerTest {

    @Autowired
    CustomerRepository customerRepository;

    private StandaloneMockMvcBuilder mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new CustomerController(customerRepository,null,null));
    }

    @Test
    public void getCustomerByCardId() throws Exception {
        customerRepository.store(new Customer("John","123", "123"));

        ResultActions perform = mockMvc.build().perform(get("/customer/123"));

        perform.andExpect(status().is(200))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.creditCard").value("123"));

    }



}