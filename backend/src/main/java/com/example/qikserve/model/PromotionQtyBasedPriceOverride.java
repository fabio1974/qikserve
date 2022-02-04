package com.example.qikserve.model;


import javax.persistence.Entity;

@Entity
public class PromotionQtyBasedPriceOverride extends Promotion {

    private Double price;

    @Override
    public Double calculateValue(Integer quantity) {
        int n = quantity/getRequiredQty();
        Product product = this.getProduct();
        return n*getRequiredQty()*product.getPrice() - n*this.getPrice();
    }

    @Override
    public String toString(){
        return getRequiredQty()+ " for £" + price;
    }


    //getters and setters

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
