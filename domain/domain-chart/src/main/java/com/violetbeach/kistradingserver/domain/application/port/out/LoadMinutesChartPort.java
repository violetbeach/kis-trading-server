package com.violetbeach.kistradingserver.domain.application.port.out;

import com.violetbeach.kistradingserver.domain.domain.MinutesChart;

public interface LoadMinutesChartPort {

    MinutesChart loadMinutesChart(LoadMinutesChartRequest request);

}
