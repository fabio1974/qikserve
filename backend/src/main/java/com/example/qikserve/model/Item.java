package com.example.qikserve.model;

import javax.persistence.*;


@Entity
public class Item {

    public Item(){
    }

    public Item(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        calculatePromotion();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Basket basket;

    private Integer quantity;

    private Double bestPromotionValue;

    public void calculatePromotion(){
        Double maxValue = 0.0;
        for(Promotion promotion : product.getPromotions()){
            Double value = promotion.calculateValue(quantity);
            maxValue = Math.max(maxValue,value);
        }
        setBestPromotionValue(maxValue);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Double getBestPromotionValue() {
        return bestPromotionValue;
    }

    public void setBestPromotionValue(Double bestPromotionValue) {
        this.bestPromotionValue = bestPromotionValue;
    }
}

