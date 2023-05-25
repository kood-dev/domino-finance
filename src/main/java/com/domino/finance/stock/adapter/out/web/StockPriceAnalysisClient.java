package com.domino.finance.stock.adapter.out.web;

import com.domino.finance.common.configuration.FeignClientConfiguration;
import com.domino.finance.stock.adapter.out.web.model.StockPriceAnalysisChart;
import com.domino.finance.stock.adapter.out.web.model.StockPriceAnalysisFilter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "stock-price-analysis-client",
        url = "${external.api.sample.self}/finance/chart",
        configuration = {FeignClientConfiguration.class}
)
public interface StockPriceAnalysisClient {

    @GetMapping("{stockCode}.KS")
    StockPriceAnalysisChart fetchStockPriceAnalysis(@PathVariable("stockCode") String stockCode,
                                                    @SpringQueryMap StockPriceAnalysisFilter filter);
}
