package com.domino.finance.stock.adapter.in.web;

import com.domino.finance.common.model.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StockPriceAnalysisController {
    private final StockPriceAnalysisControllerHelper helper;

    @GetMapping("/stock/price/analysis/{stockCode}")
    public CommonResponse findStockPriceAnalysis(@PathVariable String stockCode) {
        return CommonResponse.ok(helper.CreateAndFindStockPriceAnalysis(stockCode));
    }
}
