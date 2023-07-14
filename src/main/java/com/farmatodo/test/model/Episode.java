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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Episode implements Serializable {
    private static final long serialVersionUID = -5938673682763435907L;

    @Id
    private String id;

    @NotNull
    private LocalDateTime createAt;

    @Indexed
    @Min(1)
    private int number;

    @NotBlank
    private String name;

    @NotEmpty
    private List<@NotBlank String> characters;
}
