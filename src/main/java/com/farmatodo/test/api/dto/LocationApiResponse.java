package com.farmatodo.test.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationApiResponse {
    private String name;
    private String type;
    private String dimension;
    private int id;
}
