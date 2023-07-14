package com.farmatodo.test.controller;

import com.farmatodo.test.dto.HappyNumberData;
import com.farmatodo.test.dto.HappyNumberRequest;
import com.farmatodo.test.dto.HappyNumbersResponse;
import com.farmatodo.test.dto.SumAllNumberResponse;
import com.farmatodo.test.service.HappyNumberService;
import com.farmatodo.test.service.SumNatureNumberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UtilsControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private HappyNumberService happyNumberService;

    @Autowired
    private SumNatureNumberService sumNatureNumberService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void isHappy() throws MalformedURLException {
        HttpHeaders headers = new HttpHeaders();

        HappyNumberRequest happyNumberRequest = HappyNumberRequest.builder()
                .numbers(asList(1L, 4L, 7L))
                .build();

        HttpEntity<HappyNumberRequest> request = new HttpEntity<>(happyNumberRequest, headers);

        ResponseEntity<HappyNumbersResponse> response = restTemplate.exchange(
                buildHappyNumberUrl(),
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<HappyNumbersResponse>() {
                });

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(3, response.getBody().getNumbers().size());
        assertEquals(HappyNumberData.builder()
                .error(null)
                .isHappy(true)
                .number(1)
                .build(), response.getBody().getNumbers().get(0));
        assertEquals(HappyNumberData.builder()
                .error("“no es posible calcular si es feliz o no”")
                .isHappy(false)
                .number(4)
                .build(), response.getBody().getNumbers().get(1));
        assertEquals(HappyNumberData.builder()
                .error(null)
                .isHappy(true)
                .number(7)
                .build(), response.getBody().getNumbers().get(2));
    }

    @Test
    public void sum() throws MalformedURLException {
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        ResponseEntity<SumAllNumberResponse> response = restTemplate.exchange(
                buildSumNumbersUrl(5),
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<SumAllNumberResponse>() {
                });

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(15, response.getBody().getResult());
    }

    private String buildHappyNumberUrl() throws MalformedURLException {
        return new URL("http://localhost:" + port +
                "/api/farmatodo-test/utils/happy/number"
        ).toString();
    }

    private String buildSumNumbersUrl(int num) throws MalformedURLException {
        return new URL("http://localhost:" + port +
                "/api/farmatodo-test/utils/sum/" + num
        ).toString();
    }
}
