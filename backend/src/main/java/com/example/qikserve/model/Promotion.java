package com.example.qikserve.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PromotionBuyXGetYFree.class, name = "BUY_X_GET_Y_FREE"),
        @JsonSubTypes.Type(value = PromotionQtyBasedPriceOverride.class, name = "QTY_BASED_PRICE_OVERRIDE"),
})
public abstract class Promotion {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private Integer requiredQty;

    private String description;

    //getters and setters

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

    public Integer getRequiredQty() {
        return requiredQty;
    }

    public void setRequiredQty(Integer requiredQty) {
        this.requiredQty = requiredQty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //calculates the value saved based on the type of promotion
    // and quantity of products for the item
    public abstract Double calculateValue(Integer quantity);
}
