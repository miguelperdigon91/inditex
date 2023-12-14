package com.inditex.test.service;

import com.inditex.test.dto.PriceRequest;
import com.inditex.test.exceptions.PriceNotFoundException;
import com.inditex.test.repository.PriceRepository;
import com.inditex.test.dto.PriceResponse;
import com.inditex.test.model.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferSearcher {
    private final PriceRepository priceRepository;

    private static final int FIRST_OFFER = 0;

    public PriceResponse search(PriceRequest request) {
        log.info("Arrives request [{}] to find final price to apply", request);

        Pageable pageable = PageRequest.of(0, 1);

        Page<Price> result = priceRepository.findPrices(
                        request.getApplicationDate(),
                        request.getProductId(),
                        request.getBrandName(),
                        pageable);

        if (result.getContent().isEmpty()) {
            log.error("Required Offer does not exist [{}]", request);

            throw new PriceNotFoundException();
        }

        Price offer = result.getContent().get(FIRST_OFFER);

        log.info("Found offer that has final price [{} {}]", offer.getPrice(), offer.getCurrency());
        return PriceResponse.builder()
                .startDate(offer.getStartDate())
                .endDate(offer.getEndDate())
                .finalPrice(offer.getPrice())
                .priceList(offer.getPriceList())
                .brandName(offer.getBrand().getName())
                .productId(offer.getProductId())
                .build();
    }
}
