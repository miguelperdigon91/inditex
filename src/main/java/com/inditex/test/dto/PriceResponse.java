package com.inditex.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponse {
    private long productId;
    private String brandName;
    private long priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal finalPrice;
}
