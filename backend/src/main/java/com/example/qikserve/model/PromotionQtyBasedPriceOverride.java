package com.example.qikserve.model;


import javax.persistence.Entity;

@Entity
public class PromotionQtyBasedPriceOverride extends Promotion {

    private Double price;

    @Override
    public Double calculateValue(Integer quantity) {
        int n = quantity/getRequiredQty();
        var amountCheaper = n * this.getPrice();
        Product product = this.getProduct();
        return quantity * product.getPrice() - amountCheaper;
    }


    //getters and setters

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
