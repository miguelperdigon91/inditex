package com.inditex.test.service;

import com.inditex.test.dto.PriceRequest;
import com.inditex.test.exceptions.PriceNotFoundException;
import com.inditex.test.model.Price;
import com.inditex.test.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferSearcher {
    private static final int FIRST_OFFER = 0;
    private final PriceRepository priceRepository;

    public Price search(PriceRequest request) {
        log.info("Arrives request [{}] to find final price to apply", request);

        List<Price> offers = priceRepository.findPrices(
                request.getApplicationDate(),
                request.getProductId(),
                request.getBrandName());

        if (offers.isEmpty()) {
            log.error("Required Offer does not exist [{}]", request);

            throw new PriceNotFoundException();
        }

        offers.sort(Comparator.comparingInt(Price::getPriority).reversed());
        Price offer = offers.get(FIRST_OFFER);

        log.info("Found offer that has final price [{} {}]", offer.getPrice(), offer.getCurrency());
        return offer;
    }
}
