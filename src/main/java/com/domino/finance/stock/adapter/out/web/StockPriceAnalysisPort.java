package com.domino.finance.stock.adapter.out.web;

import com.domino.finance.common.exception.ExternalClientCanNotProceedException;
import com.domino.finance.stock.adapter.out.web.StockPriceAnalysisClient;
import com.domino.finance.stock.adapter.out.web.model.StockPriceAnalysisChart;
import com.domino.finance.stock.adapter.out.web.model.StockPriceAnalysisFilter;
import com.domino.finance.stock.adapter.out.web.model.StockPriceAnalysisResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockPriceAnalysisPort {
    private final ObjectProvider<StockPriceAnalysisClient> stockPriceAnalysisClientProvider;

    public Optional<StockPriceAnalysisResponse> fetchStockPriceAnalysis(String stockCode) {
        try {
            StockPriceAnalysisChart response = getClient().fetchStockPriceAnalysis(stockCode, StockPriceAnalysisFilter.of());
            // smell ...
            return Optional.of(response.getResult().get(0));
        } catch (ExternalClientCanNotProceedException e) {
            log.error("Fetch stock price analysis failed.");
            // TODO configuration error handling check.
            return Optional.empty();
        }
    }

    private StockPriceAnalysisClient getClient() {
        return stockPriceAnalysisClientProvider.getIfAvailable();
    }
}
