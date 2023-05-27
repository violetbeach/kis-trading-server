package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import java.time.LocalTime;

record CandleVO(
        String stockCode,
        Long price,
        LocalTime baseTime,
        Long highPrice,
        Long lowPrice,
        Long volume
) {
}
