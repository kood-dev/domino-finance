package com.domino.finance.stock.application.port.in;


import com.domino.finance.stock.application.model.CreateDailyStockPriceCommand;

import java.util.List;

public interface StockPriceAnalysisCommandUseCase {
    void createStockPriceAnalysis(CreateDailyStockPriceCommand command);
    void createStockPriceAnalysis(List<CreateDailyStockPriceCommand> commands);
}
