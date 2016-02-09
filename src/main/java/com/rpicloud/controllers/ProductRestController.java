package com.rpicloud.controllers;

import com.rpicloud.exceptions.MongoConnectionException;
import com.rpicloud.exceptions.ProductNotFoundException;
import com.rpicloud.models.ErrorMessage;
import com.rpicloud.models.Product;
import com.rpicloud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<Collection<Product>> getAllProducts() throws MongoConnectionException {
        Collection<Product> products;
        try {
            products = repository.findAll();
        } catch (DataAccessResourceFailureException e) {
            throw new MongoConnectionException(e.getMessage(), e);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) throws MongoConnectionException, ProductNotFoundException {
        Product product;
        try {
            product = repository.findOne(id);
        } catch (DataAccessResourceFailureException e) {
            throw new MongoConnectionException(e.getMessage(), e);
        }

        if (product == null) {
            throw new ProductNotFoundException(id);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(HttpServletRequest request, Exception exception) {
        ErrorMessage error = new ErrorMessage(
                exception.getMessage(),
                exception.getStackTrace().toString(),
                request.getRequestURL().toString(),
                HttpStatus.PRECONDITION_FAILED.value());

        return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
    }
}
