package com.inditex.test.repository;

import com.inditex.test.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository  extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE :requiredDate BETWEEN p.startDate AND p.endDate AND p.productId = :productId AND p.brand.name = :brandName")
    List<Price> findPrices(LocalDateTime requiredDate, long productId, String brandName);
}
