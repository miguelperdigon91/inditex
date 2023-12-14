package com.inditex.test.repository;

import com.inditex.test.model.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PriceRepository  extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE :requiredDate BETWEEN p.startDate AND p.endDate AND p.productId = :productId AND p.brand.name = :brandName ORDER BY p.priority DESC")
    Page<Price> findPrices(LocalDateTime requiredDate, long productId, String brandName, Pageable pageable);
}
