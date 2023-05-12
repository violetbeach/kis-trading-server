package com.violetbeach.kistradingserver.domain.application.port.in;

import com.violetbeach.kistradingserver.domain.application.port.in.request.GetMinutesChartRequest;
import com.violetbeach.kistradingserver.domain.domain.MinutesChart;

public interface GetMinutesChartUseCase {

    MinutesChart getMinutesChart(GetMinutesChartRequest request);

}
