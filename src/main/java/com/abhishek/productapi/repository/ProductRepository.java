package com.abhishek.productapi.repository;

import com.abhishek.productapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("select p from Product p where p.price between :minPrice and :maxPrice")
    List<Product>searchByPriceRange(@Param("minPrice")double minPrice, @Param("maxPrice")double maxPrice);

    @Query("select p from Product p where lower(p.name) like lower(concat('%',:keyWord,'%'))")
    List<Product>searchByNameKeyword(@Param("keyWord")String keyWord);

    @Query("select p from Product p where p.quantity<:quantity order by p.quantity asc")
    List<Product>findLowStockProducts(@Param("quantity")int quantity);

    @Query("select avg(p.price) from Product p")
    Double findAveragePrice();

    @Query("select SUM(p.price * p.quantity) FROM Product p")
    Double findTotalStockValue();

}
