package com.violetbeach.kistradingserver.domain.domain;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MinutesChart {

    private final List<Candle> candles;

}
