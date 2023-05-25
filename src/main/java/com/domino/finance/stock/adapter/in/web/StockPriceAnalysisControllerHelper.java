package com.domino.finance.stock.adapter.in.web;

import com.domino.finance.stock.application.model.DailyStockPriceQuery;
import com.domino.finance.stock.application.port.in.StockPriceAnalysisCommandUseCase;
import com.domino.finance.stock.application.port.in.StockPriceAnalysisQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class StockPriceAnalysisControllerHelper {
    private final StockPriceAnalysisCommandUseCase commandUseCase;
    private final StockPriceAnalysisQueryUseCase queryUseCase;

    public List<DailyStockPriceQuery> CreateAndFindStockPriceAnalysis(String stockCode) {
        List<DailyStockPriceQuery> dailyStockPriceQueries = queryUseCase.fetchDailyStockPriceQuery(stockCode);
        commandUseCase.createStockPriceAnalysis(dailyStockPriceQueries.stream()
                .map(dsp -> dsp.toCreateDailyStockPriceCommand(stockCode, dsp))
                .collect(Collectors.toList()));
        return queryUseCase.findAll(stockCode);
    }

}
