package com.example.qikserve.controller;

import com.example.qikserve.model.Basket;
import com.example.qikserve.model.Item;
import com.example.qikserve.model.User;
import com.example.qikserve.repositories.BasketRepository;
import com.example.qikserve.service.BasketService;
import com.example.qikserve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasketController {

    @Autowired
    UserService userService;

    @Autowired
    BasketRepository basketRepository;

    @PostMapping("/baskets")
    public Basket createEmptyBasket(@RequestBody Basket basket){
        User user = userService.findOrSave(basket.getUser());
        basket.setUser(user);
        basket.getItems().forEach(item -> item.setBasket(basket));
        basketRepository.save(basket);
        return basket;
    }

    @PostMapping("/baskets/{id}/addItem")
    public Basket addItem(@PathVariable(value = "id") Long id, @RequestBody Item item){
        Basket basket = basketRepository.findById(id).orElseThrow();
        item.setBasket(basket);
        basket.getItems().add(item);
        basketRepository.save(basket);
        return basket;
    }

    @Autowired
    BasketService basketService;

    @GetMapping("/baskets/{id}/checkout")
    public Basket checkout(@PathVariable(value = "id") Long id){
        Basket basket = basketRepository.findById(id).orElseThrow();
        basketService.checkoutBasket(basket);
        return basket;
    }




}
