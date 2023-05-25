package com.domino.finance.stock.adapter.out.web.model;

import com.domino.finance.common.constant.IntervalType;
import com.domino.finance.common.constant.ValidRangeType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StockPriceAnalysisFilter {
    private String interval;
    private String range;

    public static StockPriceAnalysisFilter of() {
        return StockPriceAnalysisFilter.builder()
                .interval(IntervalType.DAY.getDesc())
                .range(ValidRangeType.FIVE_DAY.getDesc())
                .build();
    }
}
