package com.farmatodo.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Character implements Serializable {
    private static final long serialVersionUID = -853300895067275132L;

    @Id
    private String id;

    @NotNull
    private LocalDateTime createAt;

    @NotBlank
    private String name;

    @NotBlank
    private String species;

    @NotBlank
    private String gender;

    @NotBlank
    private String image;

    @NotBlank
    private String location;

    @Indexed
    @Min(1)
    private int characterId;
}
