package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import java.time.LocalTime;

record CandleVO(
        Long price,
        LocalTime baseTime,
        Long highPrice,
        Long lowPrice,
        Long volume
) {
}
