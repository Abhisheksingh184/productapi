package com.abhishek.productapi.service;

import com.abhishek.productapi.dto.ProductRequest;
import com.abhishek.productapi.dto.ProductResponse;
import com.abhishek.productapi.exception.ProductNotFoundException;
import com.abhishek.productapi.model.Product;
import com.abhishek.productapi.repository.ProductRepositoryOld;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author siabhis
 **/
@Service
public class ProductService {
    private int nextId=5;
    private final ProductRepositoryOld productRepository;
    public ProductService(ProductRepositoryOld productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> productsResponse=new ArrayList<>();
        for(Product product:productRepository.findAll()){
            productsResponse.add(mapToResponse(product,"Product fetched  successfully"));
        }
        return productsResponse;
    }
    public ProductResponse findProductById(int id) {
        Product product=productRepository.findById(id);
        if(product==null){
            throw new ProductNotFoundException(id);
        }
        return mapToResponse(product,"Product fetched successfully");
    }
    public ProductResponse addProduct(ProductRequest product){

        Product newProduct=new Product(nextId++,product.getName(),product.getPrice(),product.getQuantity());
        productRepository.save(newProduct);
        return mapToResponse(newProduct,"Product added successfully");
    }

    public ProductResponse updateProduct(int id, ProductRequest product){
        Product foundProduct=findProductEntityById(id);

        foundProduct.setName(product.getName());
        foundProduct.setPrice(product.getPrice());
        foundProduct.setQuantity(product.getQuantity());
        return mapToResponse(foundProduct,"Product updated successfully");
    }
    public boolean deleteProduct(int id){
        boolean removed=productRepository.deleteById(id);
        if(removed){
            return true;
        }
        throw new ProductNotFoundException(id);
    }

    public List<ProductResponse> findProductsByMinPrice(double minPrice){
        List<ProductResponse> filteredProducts =new ArrayList<>();
        for(Product product: productRepository.findByMinPrice(minPrice)){

                filteredProducts.add(mapToResponse(product,"Product filtered successfully"));
        }
        return filteredProducts;
    }

    private ProductResponse mapToResponse(Product product,String message){
        return new ProductResponse(product.getId(),product.getName(),product.getPrice(),product.getQuantity(),message);
    }
    private Product findProductEntityById(int id){
        Product product=productRepository.findById(id);
        if(product==null){
            throw new ProductNotFoundException(id);
        }
        return product;

    }

    public int countProducts() {
        return productRepository.countAllProducts();
    }
}
