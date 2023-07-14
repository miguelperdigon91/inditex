package com.farmatodo.test.service;

import com.farmatodo.test.dto.HappyNumberData;
import com.farmatodo.test.dto.HappyNumberRequest;
import com.farmatodo.test.dto.HappyNumbersResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
public class HappyNumberService {
    public HappyNumbersResponse areHappy(HappyNumberRequest request) {
        return HappyNumbersResponse.builder()
                .numbers(request.getNumbers().stream().map(this::analyze).collect(Collectors.toList()))
                .build();
    }

    private HappyNumberData analyze(long number) {
        int sum = 0;
        long originalNumber = number;
        int counter = 0;
        while (sum != 1 && counter < 1000) {
            sum = 0;
            while (number > 0) {
                long digit = number % 10;
                sum += digit * digit;
                number /= 10;
            }
            number = sum;
            counter++;
        }
        return HappyNumberData.builder()
                .number(originalNumber)
                .isHappy(sum == 1)
                .error(counter >= 100 ? "“no es posible calcular si es feliz o no”" : null)
                .build();
    }
}
