package com.rpicloud.repositories;

import com.rpicloud.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by kaspernissen on 08/02/2016.
 */
public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByProductName(String productName);
}
