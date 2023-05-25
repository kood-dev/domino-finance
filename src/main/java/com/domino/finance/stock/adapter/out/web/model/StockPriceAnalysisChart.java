package com.domino.finance.stock.adapter.out.web.model;

import lombok.Getter;

import java.util.List;

@Getter
public class StockPriceAnalysisChart {
    FinanceChartData chart;

    public ErrorResponse getError() {
        return getChart().getError();
    }

    public List<StockPriceAnalysisResponse> getResult() {
        return getChart().getResult();
    }
}

@Getter
class FinanceChartData {
    ErrorResponse error;
    List<StockPriceAnalysisResponse> result;
}

class ErrorResponse {
    private String code;
    private String description;
}
