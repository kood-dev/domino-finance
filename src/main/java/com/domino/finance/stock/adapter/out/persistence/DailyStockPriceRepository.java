package com.domino.finance.stock.adapter.out.persistence;

import com.domino.finance.stock.domain.DailyStockPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyStockPriceRepository extends JpaRepository<DailyStockPrice, Long> {
    List<DailyStockPrice> findByStockCode(String stockCode);
}
