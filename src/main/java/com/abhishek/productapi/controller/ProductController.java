package com.abhishek.productapi.controller;

import com.abhishek.productapi.dto.ProductRequest;
import com.abhishek.productapi.dto.ProductResponse;

import com.abhishek.productapi.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author siabhis
 **/
@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Integer id) {

        return ResponseEntity.ok(productService.findProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest product){

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Integer id,@Valid @RequestBody ProductRequest product){
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductResponse>> findProductsByMinPrice(@RequestParam double minPrice){
        if(minPrice < 0){
            return ResponseEntity.badRequest().build();
        }

        return  ResponseEntity.ok(productService.findProductsByMinPrice(minPrice));
    }
    @GetMapping("/count")
    public ResponseEntity<Map<String,Long>> countProducts(){
        Map<String,Long> map = new HashMap<>();
        map.put("totalProducts",productService.countProducts());
        return ResponseEntity.ok(map);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> findByNameContainingIgnoreCase(@RequestParam String name){
        if (name == null || name.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        List<ProductResponse>response= productService.findByNameContainingIgnoreCase(name);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/price-below")
    public ResponseEntity<List<ProductResponse>> findByPriceBelow(@RequestParam("maxPrice") double price){
        if (price < 0) return ResponseEntity.badRequest().build();
        List<ProductResponse> response=productService.findByPriceLessThan(price);
        if(response.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/price-between")
    public ResponseEntity<List<ProductResponse>>findByPriceBetween(@RequestParam("minPrice") double minPrice,@RequestParam("maxPrice") double maxPrice){
        if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
            return ResponseEntity.badRequest().build();
        }
        List<ProductResponse> response=productService.findByPriceBetween(minPrice,maxPrice);
        if(response.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductResponse>> findByLowStock(@RequestParam("quantity")int quantity){
        if (quantity < 0) return ResponseEntity.badRequest().build();
        List<ProductResponse> response=productService.findByQuantityLessThan(quantity);
        if(response.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter/sorted")
    public ResponseEntity<List<ProductResponse>> findByPriceGreaterThanEqualOrderByPriceDesc(@Positive(message = "minPrice must be greater than 0") @RequestParam("minPrice")double minPrice){
        List<ProductResponse>responses=productService.findByPriceGreaterThanEqualOrderByPriceDesc(minPrice);
        if(responses.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/query/price-range")
    public ResponseEntity<List<ProductResponse>> findByPriceRange(@RequestParam("minPrice")@PositiveOrZero double minPrice, @RequestParam("maxPrice")@PositiveOrZero double maxPrice){
        if(minPrice>maxPrice){
            return ResponseEntity.badRequest().build();
        }
        List<ProductResponse>response=productService.searchByPriceRange(minPrice,maxPrice);
        if(response.isEmpty() ){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/query/search")
    public ResponseEntity<List<ProductResponse>> findByNameContaining(@RequestParam("keyword")@NotBlank String name){
        List<ProductResponse>responses=productService.searchByNameKeyword(name);
        if(responses.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/query/low-stock")
    public ResponseEntity<List<ProductResponse>> findLowStockProducts(@RequestParam("quantity")@PositiveOrZero int quantity){
        List<ProductResponse>response=productService.findLowStockProducts(quantity);
        if(response.isEmpty()){
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stats/average-price")
    public ResponseEntity<Map<String,Double>> findAveragePrice(){
        double averagePrice = productService.findAveragePrice();
        Map<String,Double> map = new HashMap<>();
        map.put("averagePrice",averagePrice);

        return ResponseEntity.ok(map);
    }

    @GetMapping("/stats/stock-value")
    public Map<String,Double> findStockValue(){
        Double stockValue= productService.findTotalStockValue();
        Map<String,Double> map = new HashMap<>();
        map.put("totalStockValue",stockValue);
        return map;
    }

}
