package com.rpicloud.controllers;

import com.rpicloud.models.Product;
import com.rpicloud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by kaspernissen on 08/02/2016.
 */

@RestController
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    private ProductRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Product> getAllProducts(){
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Product getProductById(@PathVariable String id){
        return repository.findOne(id);
    }
}
