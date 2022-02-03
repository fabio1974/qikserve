package com.example.qikserve.controller;

import com.example.qikserve.model.Item;
import com.example.qikserve.model.Product;
import com.example.qikserve.repositories.ItemRepository;
import com.example.qikserve.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @PostMapping("/items")
    public void create(@RequestBody Item item) {


    }

    @PutMapping("/items/{id}")
    public void update(@RequestBody Item item) {

    }

    @GetMapping("/items")
    public List<Item> getPostsPage(@PathVariable(value = "id") Long id) {
        return null;
    }


}
