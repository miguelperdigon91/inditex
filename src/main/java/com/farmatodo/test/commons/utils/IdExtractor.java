package com.farmatodo.test.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IdExtractor {
    public String extract(String url, String separator) {
        String[] arr = url.split(separator);

        return arr[1];
    }
}
