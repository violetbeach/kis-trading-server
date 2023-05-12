package com.violetbeach.kistradingserver.domain.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Candle {
    private final String stockCode;
    private final Money price;
    private final String baseTime;
    private final Money highPrice;
    private final Money lowPrice;
    private final Volume volume;
}
