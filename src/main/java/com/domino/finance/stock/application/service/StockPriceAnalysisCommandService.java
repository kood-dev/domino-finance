package com.domino.finance.stock.application.service;

import com.domino.finance.common.anotation.UseCase;
import com.domino.finance.stock.application.model.CreateDailyStockPriceCommand;
import com.domino.finance.stock.application.port.in.StockPriceAnalysisCommandUseCase;
import com.domino.finance.stock.application.port.out.DailyStockPricePort;
import com.domino.finance.stock.domain.DailyStockPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@UseCase
@Transactional
class StockPriceAnalysisCommandService implements StockPriceAnalysisCommandUseCase {
    private final DailyStockPricePort dailyStockPricePort;

    @Override
    public void createStockPriceAnalysis(CreateDailyStockPriceCommand command) {
        dailyStockPricePort.create(DailyStockPrice.of(command));
    }

    @Override
    public void createStockPriceAnalysis(List<CreateDailyStockPriceCommand> commands) {
        dailyStockPricePort.createAll(commands.stream().map(DailyStockPrice::of).collect(Collectors.toList()));
    }
}
