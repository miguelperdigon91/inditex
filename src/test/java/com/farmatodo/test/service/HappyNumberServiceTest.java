package com.farmatodo.test.service;

import com.farmatodo.test.dto.HappyNumberRequest;
import com.farmatodo.test.dto.HappyNumbersResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.google.common.primitives.Longs.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class HappyNumberServiceTest {
    @InjectMocks
    private HappyNumberService service;

    @Test
    public void areHappy() {
        HappyNumberRequest request = HappyNumberRequest.builder()
                .numbers(asList(1, 4))
                .build();

        HappyNumbersResponse response = service.areHappy(request);

        assertTrue(response.getNumbers().get(0).getIsHappy());
        assertFalse(response.getNumbers().get(1).getIsHappy());
    }
}
