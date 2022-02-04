package com.example.qikserve.controller;

import com.example.qikserve.model.Product;
import com.example.qikserve.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/products")
    public void create(@RequestBody Product product){
        product.getPromotions().forEach(p->{p.setProduct(product);});
        productRepository.save(product);
    }

    @GetMapping("/products")
    public List<Product> getPostsPage() {
        return productRepository.findAll();
    }

}
