package com.farmatodo.test.service;

import com.farmatodo.test.dto.SumAllNumberResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SumNatureNumberService {
    public SumAllNumberResponse sum(int number) {
        int sum = number;

        while(number > 0) {
            sum += number - 1;
            number --;
        }

        return SumAllNumberResponse.builder()
                .result(sum)
                .build();
    }
}
