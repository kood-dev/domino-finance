package com.domino.finance.stock.adapter.out.web.model;

import com.domino.finance.stock.application.model.DailyStockPriceQuery;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class StockPriceAnalysisResponse {
    StockPriceAnalysisIndicators indicators;
    Map<String, Object> meta;
    List<Long> timestamp;

    public List<DailyStockPriceQuery> convertDailyStockPriceQueries() {
        StockPriceAnalysisQuote stockPriceAnalysisQuote = indicators.getQuote().get(0);

        return IntStream.range(0, Math.min(stockPriceAnalysisQuote.getOpen().size(), timestamp.size()))
                .mapToObj(i -> DailyStockPriceQuery.builder()
                        .open(stockPriceAnalysisQuote.getOpen().get(i))
                        .close(stockPriceAnalysisQuote.getClose().get(i))
                        .low(stockPriceAnalysisQuote.getLow().get(i))
                        .high(stockPriceAnalysisQuote.getHigh().get(i))
                        .volume(stockPriceAnalysisQuote.getVolume().get(i))
                        .transactionTimestamp(timestamp.get(i))
                        .build())
                .collect(Collectors.toList());
    }
}
