package com.farmatodo.test.commons.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class DateUtilTest {
    @Test
    public void now() {
        DateUtil dateUtil = new DateUtil();

        LocalDateTime res = dateUtil.now();

        assertNotNull(res);
        assertTrue(res.isBefore(LocalDateTime.now().plusSeconds(2)));
        assertTrue(res.isAfter(LocalDateTime.now().minusSeconds(2)));
    }
}
