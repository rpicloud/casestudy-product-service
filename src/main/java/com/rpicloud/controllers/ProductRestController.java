package com.rpicloud.controllers;

import com.rpicloud.models.Product;
import com.rpicloud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by kaspernissen on 08/02/2016.
 */

@RestController
public class ProductRestController {

    @Autowired
    private ProductRepository repository;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public Collection<Product> getAllProducts(){
        return repository.findAll();
    }
}
