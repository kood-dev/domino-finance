package com.domino.finance.stock.adapter.out.web.model;

import lombok.Getter;

import java.util.List;

@Getter
public class StockPriceAnalysisIndicators {
    List<StockPriceAnalysisQuote> quote;

}

@Getter
class StockPriceAnalysisQuote {
    List<Integer> close;
    List<Integer> high;
    List<Integer> low;
    List<Integer> open;
    List<Integer> volume;
}
