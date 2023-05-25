package com.domino.finance.stock.application.service;

import com.domino.finance.common.anotation.UseCase;
import com.domino.finance.stock.adapter.out.web.StockPriceAnalysisPort;
import com.domino.finance.stock.adapter.out.web.model.StockPriceAnalysisResponse;
import com.domino.finance.stock.application.model.DailyStockPriceQuery;
import com.domino.finance.stock.application.port.in.StockPriceAnalysisQueryUseCase;
import com.domino.finance.stock.application.port.out.DailyStockPricePort;
import com.domino.finance.stock.domain.DailyStockPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@UseCase
@Transactional(readOnly = true)
class StockPriceAnalysisQueryService implements StockPriceAnalysisQueryUseCase {
    private final StockPriceAnalysisPort stockPriceAnalysisPort;
    private final DailyStockPricePort dailyStockPricePort;

    @Override
    public List<DailyStockPriceQuery> fetchDailyStockPriceQuery(String stockCode) {
        Optional<StockPriceAnalysisResponse> response = stockPriceAnalysisPort.fetchStockPriceAnalysis(stockCode);
        return response.map(StockPriceAnalysisResponse::convertDailyStockPriceQueries).orElse(List.of());
    }

    @Override
    public List<DailyStockPriceQuery> findAll(String stockCode) {
        return dailyStockPricePort.findAll(stockCode).stream().map(DailyStockPriceQuery::of).collect(Collectors.toList());
    }
}
