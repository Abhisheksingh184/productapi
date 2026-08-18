package com.abhishek.productapi.exception;

/**
 * @author siabhis
 **/
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super("Product not found with id : "+id);
    }

}
