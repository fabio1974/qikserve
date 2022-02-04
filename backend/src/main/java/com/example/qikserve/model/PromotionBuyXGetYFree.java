package com.example.qikserve.model;

import javax.persistence.*;

@Entity
public class PromotionBuyXGetYFree extends Promotion {

    private Integer freeQty;

    @Override
    public Double calculateValue(Integer quantity) {
        int n = quantity/getRequiredQty();
        var quantityFree = quantity - n*getRequiredQty();
        Product product = this.getProduct();
        return product.getPrice() * quantityFree;
    }


    //getters and setters

    public Integer getFreeQty() {
        return freeQty;
    }

    public void setFreeQty(Integer freeQty) {
        this.freeQty = freeQty;
    }


}
