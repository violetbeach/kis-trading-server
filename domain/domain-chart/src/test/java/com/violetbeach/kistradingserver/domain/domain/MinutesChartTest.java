package com.violetbeach.kistradingserver.domain.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static fixture.domain.CandleFixture.삼성전자_분봉;
import static fixture.domain.MinutesChartFixture.삼성전자_차트;

class MinutesChartTest {
    StockCode stockCode;
    List<Candle> candles;

    @BeforeEach
    public void setup() {
        stockCode = new StockCode(삼성전자_차트.stockCode());
        candles = List.of(
                삼성전자_분봉.getCandle(),
                삼성전자_분봉.getCandle()
        );
    }

    @Test
    @DisplayName("getStockCode()는 주식 코드를 반환한다.")
    public void testGetStockCode() {
        MinutesChart minutesChart = new MinutesChart(stockCode, candles);
        Assertions.assertEquals(삼성전자_차트.stockCode(), minutesChart.getStockCode());
    }

    @Test
    @DisplayName("getCandlesSize()는 candles의 개수를 반환한다.")
    public void testGetCandlesSize() {
        MinutesChart minutesChart = new MinutesChart(stockCode, candles);
        Assertions.assertEquals(2, minutesChart.getCandlesSize());
    }
}