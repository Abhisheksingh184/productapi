package com.abhishek.productapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * @author siabhis
 **/
public class ProductRequest {

    @NotBlank(message="Product name is required")
    private String name;
    @Positive(message="Price must be greater than 0")
    private double price;
    @PositiveOrZero(message = "Quantity cannot be negative")
    private int quantity;

    public ProductRequest() {

    }
    public ProductRequest(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
