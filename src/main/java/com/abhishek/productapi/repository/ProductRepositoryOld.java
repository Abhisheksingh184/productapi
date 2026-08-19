package com.abhishek.productapi.repository;

import com.abhishek.productapi.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author siabhis
 **/
@Repository
public class ProductRepositoryOld {

    private final List<Product> products=new ArrayList<Product>();
    public ProductRepositoryOld() {
        products.add(new Product(1,"Laptop",2000,4));
        products.add(new Product(2,"Tablet",2000,4));
        products.add(new Product(3,"Desktop",2000,4));
        products.add(new Product(4,"Mobile",2000,4));
    }

    public List<Product> findAll() {
        return products;
    }
    public Product findById(int id) {
        for(Product product:products){
            if(product.getId()==id){
                return product;
            }
        }
        return null;
    }

    public Product save(Product product) {
        products.add(product);
        return product;
    }


    public boolean deleteById(int id) {
        return products.removeIf(product->product.getId()==id);
    }

    public List<Product> findByMinPrice(double minPrice) {
        List<Product> filteredProducts=new ArrayList<>();
        for(Product product:products){
            if(product.getPrice()>=minPrice){
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    public int countAllProducts() {
        return products.size();
    }


}
