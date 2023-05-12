package com.violetbeach.kistradingserver.domain.application.port.out;

public record LoadMinutesChartRequest(
        String stockCode,
        String time
) {
}
