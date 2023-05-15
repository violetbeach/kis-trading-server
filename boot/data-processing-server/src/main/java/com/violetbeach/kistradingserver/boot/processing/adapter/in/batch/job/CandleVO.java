package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import lombok.Getter;

import java.time.LocalTime;

@Getter
record CandleVO(
        Long price,
        LocalTime baseTime,
        Long highPrice,
        Long lowPrice,
        Long volume
) {
}
