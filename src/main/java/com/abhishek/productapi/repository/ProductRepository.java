package com.abhishek.productapi.repository;

import com.abhishek.productapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author siabhis
 **/
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
