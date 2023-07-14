package com.farmatodo.test.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterApiResponse {
    private String name;
    private String species;
    private String gender;
    private String image;
    private CharacterLocation location;
    private int id;
}
