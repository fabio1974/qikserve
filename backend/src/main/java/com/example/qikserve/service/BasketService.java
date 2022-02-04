package com.example.qikserve.service;

import com.example.qikserve.model.Basket;
import com.example.qikserve.model.Item;
import com.example.qikserve.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BasketService {

    public void checkoutBasket(Basket basket) {
        //grouping the items of the same product to better apply the promotion
        Map<Product,Integer> groupedItems = basket.getItems()
                .stream()
                .collect(Collectors.groupingBy(Item::getProduct,Collectors.summingInt(Item::getQuantity)));

        //mapping the Map to a List of Item
        List<Item> newItems = groupedItems.entrySet().stream().map(e-> new Item(e.getKey(),e.getValue())).collect(Collectors.toList());

        //calculating the total without any promotion
        Double total = newItems.stream().mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice()).sum();
        basket.setTotal(total);

        //summing the promotion of each basket item
        Double saved = newItems.stream().mapToDouble(Item::getBestPromotionValue).sum();
        basket.setTotalSaved(saved);

        //calculating the difference to get the total payble value
        basket.setTotalPayble(total-saved);

        //recreating the grouped items in the sane basked object to return to frontend
        basket.getItems().clear();
        basket.setItems(newItems);
    }


}
