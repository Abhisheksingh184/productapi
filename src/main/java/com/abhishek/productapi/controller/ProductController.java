package com.abhishek.productapi.controller;

import com.abhishek.productapi.dto.ProductRequest;
import com.abhishek.productapi.dto.ProductResponse;

import com.abhishek.productapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author siabhis
 **/
@RestController
@RequestMapping("/api/products")
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
    public ResponseEntity<Map<String,Integer>> countProducts(){
        Map<String,Integer> map = new HashMap<>();
        map.put("totalProducts",productService.countProducts());
        return ResponseEntity.ok(map);
    }

}
