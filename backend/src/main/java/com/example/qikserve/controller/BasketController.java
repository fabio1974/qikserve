package com.example.qikserve.controller;

import com.example.qikserve.model.Basket;
import com.example.qikserve.model.Item;
import com.example.qikserve.model.Product;
import com.example.qikserve.model.User;
import com.example.qikserve.repositories.BasketRepository;
import com.example.qikserve.repositories.UserRepository;
import com.example.qikserve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BasketController {

    @Autowired
    UserService userService;

    @Autowired
    BasketRepository basketRepository;

    @PostMapping("/baskets")
    public void create(@RequestBody Basket basket){
        User user = userService.findOrSave(basket.getUser());
        basket.setUser(user);
        basketRepository.save(basket);
    }

    @PostMapping("/baskets/{id}/addItem")
    public void create(@PathVariable(value = "id") Long id, @RequestBody Item item){
        Basket basket = basketRepository.findById(id).orElse(null);
        item.setBasket(basket);
        basket.getItems().add(item);
        basketRepository.save(basket);
    }

    @GetMapping("/baskets")
    public List<Basket> create(){
        var iterator = basketRepository.findAll().iterator();
        List<Basket> baskets = new ArrayList<>();
        while (iterator.hasNext()) {
            var basket = iterator.next();
            var items = basket.getItems();
            Map<Product,Integer> sum = items.stream().collect(Collectors.groupingBy(Item::getProduct,Collectors.summingInt(Item::getQuantity)));
            List<Item> newItems = sum.entrySet().stream().map(e-> new Item(e.getKey(),e.getValue())).collect(Collectors.toList());
            basket.getItems().clear();
            basket.setItems(newItems);
            baskets.add(basket);
        }
        return baskets;
    }


}
