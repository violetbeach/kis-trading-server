package com.violetbeach.kistradingserver.domain.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class MinutesChart {

    private final StockCode stockCode;
    private final List<Candle> candles;

    public String getStockCode() {
        return stockCode.getCode();
    }
    public int getCandlesSize() {
        return candles.size();
    }

}
