package com.inditex.test.controller;

import com.inditex.test.dto.PriceRequest;
import com.inditex.test.dto.PriceResponse;
import com.inditex.test.model.Price;
import com.inditex.test.service.OfferSearcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("price")
@RequiredArgsConstructor
public class PriceController {
    private final OfferSearcher offerSearcher;

    @GetMapping("product-id/{product_id}/brand/{brand_name}")
    public ResponseEntity<PriceResponse> get(
            @PathVariable("product_id") @Min(1) long productId,
            @PathVariable("brand_name") @NotBlank String brandName,
            @RequestParam("application-date") LocalDateTime applicationDate) {
        log.info("Trying to find final price to apply for product Id [{}] at date [{}]", productId, applicationDate);

        PriceRequest request = PriceRequest.builder()
                .brandName(brandName)
                .productId(productId)
                .applicationDate(applicationDate)
                .build();

        Price offer = offerSearcher.search(request);

        return ok(PriceResponse.builder()
                .startDate(offer.getStartDate())
                .endDate(offer.getEndDate())
                .finalPrice(offer.getPrice())
                .priceList(offer.getPriceList())
                .brandName(offer.getBrand().getName())
                .productId(offer.getProductId())
                .build());
    }
}
