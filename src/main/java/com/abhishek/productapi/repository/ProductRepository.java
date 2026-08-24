package com.abhishek.productapi.repository;

import com.abhishek.productapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author siabhis
 **/
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByPriceGreaterThan(double minPrice);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByPriceLessThan(double price);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByQuantityLessThan(int quantity);
    List<Product> findByPriceGreaterThanEqualOrderByPriceDesc(double minPrice);

}
