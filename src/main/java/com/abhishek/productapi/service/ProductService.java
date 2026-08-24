package com.abhishek.productapi.service;

import com.abhishek.productapi.dto.ProductRequest;
import com.abhishek.productapi.dto.ProductResponse;
import com.abhishek.productapi.exception.ProductNotFoundException;
import com.abhishek.productapi.model.Product;
import com.abhishek.productapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author siabhis
 **/
@Service
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> productsResponse=new ArrayList<>();
        for(Product product:productRepository.findAll()){
            productsResponse.add(mapToResponse(product,"Product fetched successfully"));
        }
        return productsResponse;
    }
    public ProductResponse findProductById(int id) {
        Product product=productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException(id));
        return mapToResponse(product,"Product fetched successfully");
    }
    public ProductResponse addProduct(ProductRequest product){

        Product newProduct=new Product(product.getName(),product.getPrice(),product.getQuantity());
        Product savedProduct= productRepository.save(newProduct);
        return mapToResponse(savedProduct,"Product added successfully");
    }

    public ProductResponse updateProduct(int id, ProductRequest product){
        Product foundProduct=productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));

        foundProduct.setName(product.getName());
        foundProduct.setPrice(product.getPrice());
        foundProduct.setQuantity(product.getQuantity());
        Product updatedProduct=productRepository.save(foundProduct);
        return mapToResponse(updatedProduct,"Product updated successfully");
    }
    public void deleteProduct(int id){
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }

    public List<ProductResponse> findProductsByMinPrice(double minPrice){
        List<ProductResponse> filteredProducts =new ArrayList<>();
        for(Product product: productRepository.findByPriceGreaterThan(minPrice)){

                filteredProducts.add(mapToResponse(product,"Product filtered successfully"));
        }
        return filteredProducts;
    }

    private ProductResponse mapToResponse(Product product,String message){
        return new ProductResponse(product.getId(),product.getName(),product.getPrice(),product.getQuantity(),message);
    }

    public long countProducts() {
        return productRepository.count();
    }

    public List<ProductResponse> findByPriceLessThan(double minPrice) {
        List<ProductResponse> productsResponse=new ArrayList<>();
        for(Product product:productRepository.findByPriceLessThan(minPrice)){
            productsResponse.add(mapToResponse(product,"Product fetched successfully"));

        }
        return productsResponse;
    }
    public List<ProductResponse> findByPriceBetween(double minPrice,double maxPrice) {
        List<ProductResponse> productsResponse=new ArrayList<>();
        for(Product product:productRepository.findByPriceBetween(minPrice,maxPrice)){
            productsResponse.add(mapToResponse(product,"Product fetched successfully"));
        }
        return productsResponse;
    }
    public List<ProductResponse> findByPriceGreaterThan(double minPrice) {
        List<ProductResponse> productsResponse=new ArrayList<>();
        for(Product product:productRepository.findByPriceGreaterThan(minPrice)){
            productsResponse.add(mapToResponse(product,"Product fetched successfully"));
        }
        return productsResponse;
    }

    public List<ProductResponse>findByNameContainingIgnoreCase(String name) {
        List<ProductResponse> productsResponse=new ArrayList<>();
        for(Product product:productRepository.findByNameContainingIgnoreCase(name)){
            productsResponse.add(mapToResponse(product,"Product fetched successfully"));
        }
        return productsResponse;
    }

    public List<ProductResponse>findByQuantityLessThan(int quantity) {
        List<ProductResponse> productsResponse=new ArrayList<>();
        for(Product product:productRepository.findByQuantityLessThan(quantity)){
            productsResponse.add(mapToResponse(product,"Product fetched successfully"));
        }
        return productsResponse;
    }
    public List<ProductResponse>findByPriceGreaterThanEqualOrderByPriceDesc(double minPrice) {
        List<ProductResponse> productsResponse=new ArrayList<>();
        for(Product product:productRepository.findByPriceGreaterThanEqualOrderByPriceDesc(minPrice)){
            productsResponse.add(mapToResponse(product,"Product fetched successfully"));
        }
        return productsResponse;
    }


}
