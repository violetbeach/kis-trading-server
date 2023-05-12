package com.violetbeach.kistradingserver.domain.application.port.in.request;

public record GetMinutesChartRequest(
        String stockCode,
        String time
) {
}
