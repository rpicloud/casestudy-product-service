package com.rpicloud.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaspernissen on 08/02/2016.
 */

@RestController
public class ProductRestController {

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<String> getAllProducts(){
        List<String> test = new ArrayList<String>();
        test.add("Hello");
        test.add("World");
        test.add("!");
        return test;
    }
}
