package com.rpicloud.controllers;

import com.rpicloud.ProductServiceApplication;
import com.rpicloud.models.Product;
import com.rpicloud.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by kaspernissen on 09/02/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductServiceApplication.class)
@WebAppConfiguration
public class ProductRestControllerTests extends BaseControllerTests {

    protected List<Product> productList;

    @Autowired
    protected ProductRepository productRepository;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.productRepository.deleteAll();
        this.productList = new ArrayList<>();
        this.productList.add(productRepository.save(new Product("Microservices By Sam Newman", "Paperback", 23.45, 34.67)));
        this.productList.add(productRepository.save(new Product("Cloud Native By Josh Long", "Paperback", 24.85, 33.69)));
        this.productList.add(productRepository.save(new Product("Cloud Computing Bible By Someone", "Paperback", 16.87, 14.42)));
    }


    @Test
    public void readAllProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].productName", is(this.productList.get(0).getProductName())));
    }

}