package com.rpicloud.models;

import org.springframework.data.annotation.Id;

/**
 * Created by kaspernissen on 08/02/2016.
 */
public class Product {

    @Id
    private String id;

    private String productName;
    private String description;
    private Double salesPrice;
    private Double costPrice;

    //for JDBC
    private Product(){};

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Product(String productName, String description, Double salesPrice, Double costPrice) {
        this.productName = productName;
        this.description = description;
        this.salesPrice = salesPrice;
        this.costPrice = costPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", salesPrice=" + salesPrice +
                ", costPrice=" + costPrice +
                '}';
    }
}
