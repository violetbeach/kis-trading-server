package com.violetbeach.kistradingserver.domain.adapter.out.web;

import com.violetbeach.kistradingserver.domain.application.port.out.LoadMinutesChartPort;
import com.violetbeach.kistradingserver.domain.application.port.out.LoadMinutesChartRequest;
import com.violetbeach.kistradingserver.domain.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
class KisChartAdapter implements LoadMinutesChartPort {
    private final KISChartClient kisChartClient;

    @Override
    public MinutesChart loadMinutesChart(LoadMinutesChartRequest request) {
        GetMinutesChartCommand command = initCommand(request);
        GetMinutesChartResponse response = kisChartClient.getMinutesChart(command);

        return makeChart(request.stockCode(), response);
    }

    private MinutesChart makeChart(String stockCode, GetMinutesChartResponse response) {
        return new MinutesChart(new StockCode(stockCode), getCandles(response));
    }

    private List<Candle> getCandles(GetMinutesChartResponse response) {
        return response.candleResponseList()
                .stream()
                .map(candleResponse -> new Candle(
                        new Money(candleResponse.price()),
						LocalDateTime.of(
								candleResponse.baseDate(),
								candleResponse.baseTime()
						),
                        new Money(candleResponse.highPrice()),
                        new Money(candleResponse.lowPrice()),
                        new Volume(candleResponse.volume())
                ))
                .toList();
    }

    private GetMinutesChartCommand initCommand(LoadMinutesChartRequest request) {
        return new GetMinutesChartCommand(
                request.stockCode(),
                request.time()
        );
    }

}
