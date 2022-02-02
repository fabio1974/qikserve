package com.example.qikserve.controller;

import com.example.qikserve.model.Promotion;
import com.example.qikserve.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PromotionController {

    @Autowired
    PromotionRepository promotionRepository;

    @PostMapping("/promotions")
    public void create(@RequestBody Promotion promotion){
        promotionRepository.save(promotion);
    }

    @PutMapping("/promotions/{id}")
    public void update(@RequestBody Promotion promotion){
        promotionRepository.save(promotion);
    }

    @GetMapping("/promotions/{id}")
    public Promotion getPostsPage(@PathVariable(value = "id") Long id) {
        return promotionRepository.findById(id).orElse(null);
    }


}
