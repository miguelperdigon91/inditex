package com.farmatodo.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HappyNumberData {
    private long number;
    private Boolean isHappy;
    private String error;
}
