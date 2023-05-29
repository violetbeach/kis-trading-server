package com.violetbeach.kistradingserver.domain.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
public class Candle {
    private final Money price;
    private final LocalTime baseTime;
    private final Money highPrice;
    private final Money lowPrice;
    private final Volume volume;

    public Long getPrice() {
        return price.getValue();
    }
    public Long getHighPrice() {
        return highPrice.getValue();
    }
    public Long getLowPrice() {
        return lowPrice.getValue();
    }
    public Long getVolume() {
        return volume.getValue();
    }
}