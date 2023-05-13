package com.violetbeach.kistradingserver.domain.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class MinutesChart {

    private final String stockCode;
    private final List<Candle> candles;

}
