package com.domino.finance.stock.adapter.out.persistence;

import com.domino.finance.common.anotation.PersistenceAdapter;
import com.domino.finance.stock.application.model.DailyStockPriceQuery;
import com.domino.finance.stock.application.port.out.DailyStockPricePort;
import com.domino.finance.stock.domain.DailyStockPrice;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class DailyStockPriceAdapter implements DailyStockPricePort {
    private final DailyStockPriceRepository repository;
    @Override
    public DailyStockPrice create(DailyStockPrice dailyStockPrice) {
        return repository.save(dailyStockPrice);
    }

    @Override
    public List<DailyStockPrice> createAll(List<DailyStockPrice> dailyStockPrice) {
        return repository.saveAll(dailyStockPrice);
    }

    @Override
    public List<DailyStockPrice> findAll(String stockCode) {
        return repository.findByStockCode(stockCode);
    }
}
