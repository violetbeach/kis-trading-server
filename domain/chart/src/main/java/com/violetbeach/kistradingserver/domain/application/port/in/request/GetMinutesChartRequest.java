package com.violetbeach.kistradingserver.domain.application.port.in.request;

import java.time.LocalTime;

public record GetMinutesChartRequest(
        String stockCode,
        LocalTime time
) {
}
