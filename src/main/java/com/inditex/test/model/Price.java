package com.inditex.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Price implements Serializable {
    private static final long serialVersionUID = 5831655916923870123L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @NotNull
    @Column(name = "price_list")
    private Long priceList;

    @NotNull
    @Column(name = "product_id")
    private Long productId;

    @NotNull
    @Column(name = "priority")
    private Integer priority;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @NotBlank
    @Column(name = "curr")
    private String currency;
}
