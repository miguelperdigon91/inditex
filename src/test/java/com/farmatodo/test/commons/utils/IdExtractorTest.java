package com.farmatodo.test.commons.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class IdExtractorTest {
    @InjectMocks
    private IdExtractor idExtractor;

    @Test
    public void extract() {
        String input = "https://rickandmortyapi.com/api/character/8";
        String response = idExtractor.extract(input, "character/");

        assertEquals("8", response);
    }
}
