package com.inditex.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable {
    private static final long serialVersionUID = -853300895067275132L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createAt;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "created_by", nullable = false)
    private String createdBy;
}
