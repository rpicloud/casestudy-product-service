package com.rpicloud.repositories;

import com.rpicloud.models.Product;
import com.sun.tools.javac.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by kaspernissen on 08/02/2016.
 */
@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductRepository extends MongoRepository<Product, String> {

    public Product findByProductName(String productName);
}
