package com.inditex.test.controller;

import com.inditex.test.dto.PriceResponse;
import com.inditex.test.model.Brand;
import com.inditex.test.model.Price;
import com.inditex.test.repository.BrandRepository;
import com.inditex.test.repository.PriceRepository;
import com.inditex.test.service.converter.StringToLocalDateTimeConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import static com.inditex.test.TestDataCommons.*;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpMethod.GET;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerTest {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    StringToLocalDateTimeConverter converter;

    @BeforeEach
    public void init() {
        Brand brand = buildBrand();
        brand = brandRepository.save(brand);
        List<Price> prices = asList(
                buildPrice(
                        new BigDecimal("35.5"),
                        1,
                        brand,
                        converter.convert("2020-06-14-00.00.00"),
                        converter.convert("2020-12-31-23.59.59"),
                        0),
                buildPrice(
                        new BigDecimal("25.45"),
                        2,
                        brand,
                        converter.convert("2020-06-14-15.00.00"),
                        converter.convert("2020-06-14-18.30.00"),
                        1),
                buildPrice(
                        new BigDecimal("30.50"),
                        3,
                        brand,
                        converter.convert("2020-06-15-00.00.00"),
                        converter.convert("2020-06-15-11.00.00"),
                        1),
                buildPrice(
                        new BigDecimal("38.95"),
                        4,
                        brand,
                        converter.convert("2020-06-15-16.00.00"),
                        converter.convert("2020-12-31-23.59.59"),
                        1)
        );

        priceRepository.saveAll(prices);
    }

    @AfterEach
    public void ending() {
        priceRepository.deleteAll();
        brandRepository.deleteAll();
    }

    @Test
    @DisplayName("*Test 1: request at 10:00 a.m. on the 14th for product 35455 for brand 1 (XYZ)")
    void test1() throws Exception {
        PriceResponse expected = buildPriceResponse(converter.convert("2020-06-14-00.00.00"),
                converter.convert("2020-12-31-23.59.59"), new BigDecimal("35.50"), 35455L, 1);

        List<Price> all = priceRepository.findAll();

        ResponseEntity<PriceResponse> response = restTemplate.exchange(
                buildSearchPriceToApplyUrl("35455", "XYZ", "2020-06-14-10.00.00"),
                GET,
                null,
                PriceResponse.class);

        assertEquals(expected, response.getBody());
    }

    @Test
    @DisplayName("*Test 2: request at 4:00 p.m. on the 14th for product 35455 for brand 1 (XYZ)")
    void test2() throws Exception {
        PriceResponse expected = buildPriceResponse(converter.convert("2020-06-14-15.00.00"),
                converter.convert("2020-06-14-18.30.00"), new BigDecimal("25.45"), 35455L, 2);

        List<Price> all = priceRepository.findAll();

        ResponseEntity<PriceResponse> response = restTemplate.exchange(
                buildSearchPriceToApplyUrl("35455", "XYZ", "2020-06-14-16.00.00"),
                GET,
                null,
                PriceResponse.class);

        assertEquals(expected, response.getBody());
    }

    @Test
    @DisplayName("*Test 3: request at 9:00 p.m. on day 14th for product 35455 for brand 1 (XYZ)")
    void test3() throws Exception {
        PriceResponse expected = buildPriceResponse(converter.convert("2020-06-14-00.00.00"),
                converter.convert("2020-12-31-23.59.59"), new BigDecimal("35.50"), 35455L, 1);

        List<Price> all = priceRepository.findAll();

        ResponseEntity<PriceResponse> response = restTemplate.exchange(
                buildSearchPriceToApplyUrl("35455", "XYZ", "2020-06-14-21.00.00"),
                GET,
                null,
                PriceResponse.class);

        assertEquals(expected, response.getBody());
    }

    @Test
    @DisplayName("*Test 4: request at 10:00 a.m. on the 15th for product 35455 for brand 1 (XYZ)")
    void test4() throws Exception {
        PriceResponse expected = buildPriceResponse(converter.convert("2020-06-15-00.00.00"),
                converter.convert("2020-06-15-11.00.00"), new BigDecimal("30.50"), 35455L, 3);

        List<Price> all = priceRepository.findAll();

        ResponseEntity<PriceResponse> response = restTemplate.exchange(
                buildSearchPriceToApplyUrl("35455", "XYZ", "2020-06-15-10.00.00"),
                GET,
                null,
                PriceResponse.class);

        assertEquals(expected, response.getBody());
    }

    @Test
    @DisplayName("*Test 5: request at 9:00 p.m. on day 16th for product 35455 for brand 1 (XYZ)")
    void test5() throws Exception {
        PriceResponse expected = buildPriceResponse(converter.convert("2020-06-15-16.00.00"),
                converter.convert("2020-12-31-23.59.59"), new BigDecimal("38.95"), 35455L, 4);

        List<Price> all = priceRepository.findAll();

        ResponseEntity<PriceResponse> response = restTemplate.exchange(
                buildSearchPriceToApplyUrl("35455", "XYZ", "2020-06-16-21.00.00"),
                GET,
                null,
                PriceResponse.class);

        assertEquals(expected, response.getBody());
    }

    String buildSearchPriceToApplyUrl(String productId, String brandName, String applicationDate) throws Exception {
        return new URL("http://localhost:" + port +
                "/api/inditex-test/price/product-id/" + productId + "/brand/" + brandName) + "?application-date=" + applicationDate;
    }
}
