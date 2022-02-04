package com.example.qikserve.model;

import javax.persistence.*;

@Entity
public class PromotionBuyXGetYFree extends Promotion {

    private Integer freeQty;

    @Override
    public Double calculateValue(Integer quantity) {
        var quantityFree = (int) (quantity * ((double)freeQty/(freeQty+getRequiredQty())));
        Product product = this.getProduct();
        return product.getPrice() * quantityFree;
    }

    @Override
    public String toString(){
        return "Buy "+ getRequiredQty()+ ", Get " + freeQty;
    }

    //getters and setters

    public Integer getFreeQty() {
        return freeQty;
    }

    public void setFreeQty(Integer freeQty) {
        this.freeQty = freeQty;
    }


}
