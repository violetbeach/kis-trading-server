package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import java.time.LocalDateTime;

record CandleVO(
        String stockCode,
        Long price,
        LocalDateTime baseDateTime,
        Long highPrice,
        Long lowPrice,
        Long volume
) {
}
