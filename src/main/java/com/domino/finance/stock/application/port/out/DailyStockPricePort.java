package com.domino.finance.stock.application.port.out;

import com.domino.finance.stock.application.model.DailyStockPriceQuery;
import com.domino.finance.stock.domain.DailyStockPrice;

import java.util.List;

public interface DailyStockPricePort {
    DailyStockPrice create(DailyStockPrice dailyStockPrice);
    List<DailyStockPrice> createAll(List<DailyStockPrice> dailyStockPrices);

    List<DailyStockPrice> findAll(String stockCode);
}
