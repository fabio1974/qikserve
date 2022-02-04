package com.example.qikserve.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Item.class)
public class Item {

    public Item(){
    }

    public Item(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        var bestPromotion = calculatePromotion();
        setBestPromotionValue(bestPromotion);
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @JoinColumn()
    @ManyToOne
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Basket basket;

    private Integer quantity;

    private Double bestPromotionValue;

    public double calculatePromotion(){
        Double bestPromotion = 0.0;
        for(Promotion promotion : product.getPromotions()){
            Double value = promotion.calculateValue(quantity);
            bestPromotion = Math.max(bestPromotion,value);
        }
        return bestPromotion;
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


