package com.inditex.test.service;

import com.inditex.test.dto.PriceRequest;
import com.inditex.test.exceptions.PriceNotFoundException;
import com.inditex.test.model.Brand;
import com.inditex.test.model.Price;
import com.inditex.test.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.inditex.test.TestDataCommons.buildBrand;
import static com.inditex.test.TestDataCommons.buildPrice;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OfferSearcherTest {
    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private OfferSearcher searcher;

    @Test
    public void search_WhenAllOk_ThenSuccess() {
        List<Price> offers = buildListPrice();
        PriceRequest request = buildPriceRequest("2020-12-31-23.59.50", "XYZ", 1);
        Price expected = offers.get(3);

        when(priceRepository.findPrices(request.getApplicationDate(), request.getProductId(), request.getBrandName()))
                .thenReturn(offers);

        Price result = searcher.search(request);

        assertEquals(expected, result);
    }

    @Test
    public void search_WhenOffersIsEmpty_ThenSuccess() {
        PriceRequest request = buildPriceRequest("2020-12-31-23.59.50", "XYZ", 1);

        when(priceRepository.findPrices(request.getApplicationDate(), request.getProductId(), request.getBrandName()))
                .thenReturn(emptyList());

        assertThrows(PriceNotFoundException.class, () -> searcher.search(request));
    }

    private List<Price> buildListPrice() {
        Brand brand = buildBrand();

        return asList(
                buildPrice(
                        new BigDecimal("35.5"),
                        1,
                        brand,
                        convert("2020-06-14-00.00.00"),
                        convert("2020-12-31-23.59.59"),
                        0),
                buildPrice(
                        new BigDecimal("25.45"),
                        2,
                        brand,
                        convert("2020-06-14-15.00.00"),
                        convert("2020-06-14-18.30.00"),
                        3),
                buildPrice(
                        new BigDecimal("30.50"),
                        3,
                        brand,
                        convert("2020-06-15-00.00.00"),
                        convert("2020-06-15-11.00.00"),
                        2),
                buildPrice(
                        new BigDecimal("38.95"),
                        4,
                        brand,
                        convert("2020-06-15-16.00.00"),
                        convert("2020-12-31-23.59.59"),
                        4)
        );
    }

    private LocalDateTime convert(String source) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

        return LocalDateTime.parse(source, formatter);
    }

    private PriceRequest buildPriceRequest(String date, String brand, long product) {
        return PriceRequest.builder()
                .applicationDate(convert(date))
                .brandName(brand)
                .productId(product)
                .build();
    }
}
