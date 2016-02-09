package com.rpicloud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by kaspernissen on 09/02/2016.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product Not Found") //404
public class ProductNotFoundException extends Exception {

    private static final long serialVersionUID = -3332292346834265371L;

    public ProductNotFoundException(String id) {
        super("ProductNotFoundException with id=" + id);
    }
}