package com.domino.finance.stock.domain;

import com.domino.finance.stock.application.model.CreateDailyStockPriceCommand;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class DailyStockPrice {
    @Id
    @GeneratedValue
    private Long id;
    private String stockCode;
    private int high;
    private int low;
    private int open;
    private int close;
    private long volume;
    private long transactionTimestamp;

    private DailyStockPrice(CreateDailyStockPriceCommand command) {
        this.stockCode = command.getStockCode();
        this.high = command.getHigh();
        this.low = command.getLow();
        this.open = command.getOpen();
        this.close = command.getClose();
        this.volume = command.getVolume();
        this.transactionTimestamp = command.getTransactionTimestamp();
    }

    public static DailyStockPrice of(CreateDailyStockPriceCommand command) {
        return new DailyStockPrice(command);
    }
}
