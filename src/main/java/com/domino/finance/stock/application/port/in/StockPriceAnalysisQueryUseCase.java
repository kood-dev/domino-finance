package com.domino.finance.stock.application.port.in;


import com.domino.finance.stock.application.model.DailyStockPriceQuery;

import java.util.List;

public interface StockPriceAnalysisQueryUseCase {
    List<DailyStockPriceQuery> fetchDailyStockPriceQuery(String stockCode);

    List<DailyStockPriceQuery> findAll(String stockCode);
}
