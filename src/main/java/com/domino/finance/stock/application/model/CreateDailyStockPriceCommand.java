package com.domino.finance.stock.application.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateDailyStockPriceCommand {
    private String stockCode;
    private int high;
    private int low;
    private int open;
    private int close;
    private long volume;
    private long transactionTimestamp;
}
