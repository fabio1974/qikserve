package com.example.qikserve.controller;

import com.example.qikserve.model.Product;
import com.example.qikserve.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/products")
    public void create(@RequestBody Product product){
        product.getPromotions().forEach(p->{p.setProduct(product);});
        productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public void update(@RequestBody Product product){
        productRepository.save(product);
    }

    @GetMapping("/products")
    public Optional<Product> getPostsPage(@PathVariable(value = "id") Long id) {
        return productRepository.findById(id);
    }


}
