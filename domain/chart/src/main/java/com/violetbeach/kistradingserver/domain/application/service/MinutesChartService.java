package com.violetbeach.kistradingserver.domain.application.service;

import com.violetbeach.kistradingserver.domain.application.port.in.GetMinutesChartUseCase;
import com.violetbeach.kistradingserver.domain.application.port.in.request.GetMinutesChartRequest;
import com.violetbeach.kistradingserver.domain.application.port.out.LoadMinutesChartPort;
import com.violetbeach.kistradingserver.domain.application.port.out.LoadMinutesChartRequest;
import com.violetbeach.kistradingserver.domain.domain.MinutesChart;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class MinutesChartService implements GetMinutesChartUseCase {

    private final LoadMinutesChartPort loadMinutesChartPort;

    @Override
    public MinutesChart getMinutesChart(GetMinutesChartRequest request) {
        LoadMinutesChartRequest loadRequest = new LoadMinutesChartRequest(
                request.stockCode(),
                request.time()
        );
        return loadMinutesChartPort.loadMinutesChart(loadRequest);
    }
}
