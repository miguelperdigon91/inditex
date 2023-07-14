package com.farmatodo.test.service;

import com.farmatodo.test.dto.SumAllNumberResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SumNatureNumberServiceTest {

    @InjectMocks
    private SumNatureNumberService service;

    @Test
    public void sum_WhenReceive5_ThenReturn15() {
        SumAllNumberResponse response = service.sum(5);

        assertEquals(15, response.getResult());
    }

    @Test
    public void sum_WhenReceive10_ThenReturn55() {
        SumAllNumberResponse response = service.sum(10);

        assertEquals(55, response.getResult());
    }
}
