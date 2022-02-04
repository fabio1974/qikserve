package com.example.qikserve.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class PromotionsTests {

    @Test
    void buy2Get1FreePrice100Qty5() {
        var promotion = new PromotionBuyXGetYFree();
        promotion.setRequiredQty(2);
        promotion.setFreeQty(1);
        var product = new Product();
        product.setPrice(100.00);
        promotion.setProduct(product);
        var savedValue = promotion.calculateValue(5);
        assertEquals("OK",savedValue,100.00);
    }

    @Test
    void buy2Get1FreePrice100Qty6() {
        var promotion = new PromotionBuyXGetYFree();
        promotion.setRequiredQty(2);
        promotion.setFreeQty(1);
        var product = new Product();
        product.setPrice(100.00);
        promotion.setProduct(product);
        var savedValue = promotion.calculateValue(6);
        assertEquals("OK",savedValue,200.00);
    }

    @Test
    void buy2Get1FreePrice100Qty7() {
        var promotion = new PromotionBuyXGetYFree();
        promotion.setRequiredQty(2);
        promotion.setFreeQty(1);
        var product = new Product();
        product.setPrice(100.00);
        promotion.setProduct(product);
        var savedValue = promotion.calculateValue(7);
        assertEquals("OK",savedValue,200.00);
    }

    @Test
    void buy2Get1FreePrice100Qty8() {
        var promotion = new PromotionBuyXGetYFree();
        promotion.setRequiredQty(2);
        promotion.setFreeQty(1);
        var product = new Product();
        product.setPrice(100.00);
        promotion.setProduct(product);
        var savedValue = promotion.calculateValue(8);
        assertEquals("OK",savedValue,200.00);
    }

    @Test
    void Buy2RequiredQty2BasedPrice10Override16() {
        var promotion = new PromotionQtyBasedPriceOverride();
        promotion.setRequiredQty(2);
        promotion.setPrice(16.00);
        var product = new Product();
        product.setPrice(10.00);
        promotion.setProduct(product);
        var savedValue = promotion.calculateValue(2);
        assertEquals("OK",savedValue,4.00);
    }

    @Test
    void Buy3RequiredQty2BasedPrice10Override16() {
        var promotion = new PromotionQtyBasedPriceOverride();
        promotion.setRequiredQty(2);
        promotion.setPrice(16.00);
        var product = new Product();
        product.setPrice(10.00);
        promotion.setProduct(product);
        var savedValue = promotion.calculateValue(3);
        assertEquals("OK",savedValue,4.00);
    }

    @Test
    void Buy4RequiredQty2BasedPrice10Override16() {
        var promotion = new PromotionQtyBasedPriceOverride();
        promotion.setRequiredQty(2);
        promotion.setPrice(16.00);
        var product = new Product();
        product.setPrice(10.00);
        promotion.setProduct(product);
        var savedValue = promotion.calculateValue(4);
        assertEquals("OK",savedValue,8.00);
    }

    @Test
    void Buy5RequiredQty2BasedPrice10Override16() {
        var promotion = new PromotionQtyBasedPriceOverride();
        promotion.setRequiredQty(2);
        promotion.setPrice(16.00);
        var product = new Product();
        product.setPrice(10.00);
        promotion.setProduct(product);
        var savedValue = promotion.calculateValue(5);
        assertEquals("OK",savedValue,8.00);
    }

}
