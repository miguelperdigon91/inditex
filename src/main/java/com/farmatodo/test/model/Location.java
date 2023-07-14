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
public class Location implements Serializable {
    private static final long serialVersionUID = 4577403784380922210L;

    @Id
    private String id;

    @NotNull
    private LocalDateTime createAt;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String dimension;

    @Indexed
    @Min(1)
    private int locationId;
}
