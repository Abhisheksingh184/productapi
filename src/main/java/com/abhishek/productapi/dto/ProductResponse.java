package com.abhishek.productapi.dto;

/**
 * @author siabhis
 **/
public class ProductResponse {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String message;
    public ProductResponse() {

    }
    public ProductResponse(int id, String name, double price, int quantity, String message) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.message = message;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
