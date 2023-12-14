package com.inditex.test;

import com.inditex.test.dto.PriceResponse;
import com.inditex.test.model.Brand;
import com.inditex.test.model.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TestDataCommons {
    private TestDataCommons() {
    }

    public static Price buildPrice(
            BigDecimal price,
            long priceList,
            Brand brand,
            LocalDateTime start,
            LocalDateTime end,
            int priority) {
        return Price.builder()
                .price(price)
                .priceList(priceList)
                .brand(brand)
                .currency("EUR")
                .endDate(end)
                .startDate(start)
                .priority(priority)
                .productId(35455L)
                .build();
    }

    public static Brand buildBrand() {
        return Brand.builder()
                .createAt(LocalDateTime.now())
                .createdBy("SYSTEM")
                .name("XYZ")
                .build();
    }

    public static PriceResponse buildPriceResponse(LocalDateTime start, LocalDateTime end, BigDecimal price, long productId, int priceList) {
        return PriceResponse.builder()
                .brandName("XYZ")
                .endDate(end)
                .finalPrice(price)
                .priceList(priceList)
                .productId(productId)
                .startDate(start)
                .build();
    }
}
