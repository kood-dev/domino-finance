package com.domino.finance.stock.application.model;

import com.domino.finance.stock.domain.DailyStockPrice;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DailyStockPriceQuery {
    private String stockCode;
    private int high;
    private int low;
    private int open;
    private int close;
    private long volume;
    private long transactionTimestamp;

    public static CreateDailyStockPriceCommand toCreateDailyStockPriceCommand(String stockCode, DailyStockPriceQuery dailyStockPrice) {
        return CreateDailyStockPriceCommand.builder()
                .stockCode(stockCode)
                .open(dailyStockPrice.getOpen())
                .close(dailyStockPrice.getClose())
                .low(dailyStockPrice.getClose())
                .high(dailyStockPrice.getHigh())
                .volume(dailyStockPrice.getVolume())
                .transactionTimestamp(dailyStockPrice.getTransactionTimestamp())
                .build();
    }

    public static DailyStockPriceQuery of(DailyStockPrice dailyStockPrice) {
        return DailyStockPriceQuery.builder()
                .stockCode(dailyStockPrice.getStockCode())
                .open(dailyStockPrice.getOpen())
                .close(dailyStockPrice.getClose())
                .low(dailyStockPrice.getClose())
                .high(dailyStockPrice.getHigh())
                .volume(dailyStockPrice.getVolume())
                .transactionTimestamp(dailyStockPrice.getTransactionTimestamp())
                .build();
    }
}
