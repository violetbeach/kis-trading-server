package com.violetbeach.kistradingserver.domain.application.port.out;

import java.time.LocalTime;

public record LoadMinutesChartRequest(
        String stockCode,
        LocalTime time
) {
}
