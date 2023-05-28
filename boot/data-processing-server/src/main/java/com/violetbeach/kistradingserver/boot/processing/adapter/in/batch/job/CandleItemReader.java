package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import com.violetbeach.kistradingserver.domain.application.port.in.GetMinutesChartUseCase;
import com.violetbeach.kistradingserver.domain.application.port.in.request.GetMinutesChartRequest;
import com.violetbeach.kistradingserver.domain.domain.Candle;
import com.violetbeach.kistradingserver.domain.domain.MinutesChart;
import org.springframework.batch.item.ItemReader;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

class CandleItemReader implements ItemReader<Candle> {
    private final Queue<Candle> candleQueue;

    CandleItemReader(final GetMinutesChartUseCase useCase, final String stockCode, DateTimeJobParameter jobParameter) {
        LocalDateTime requestDateTime = jobParameter.getBaseDateTime();

        GetMinutesChartRequest request = new GetMinutesChartRequest(stockCode, requestDateTime.toLocalTime());
        MinutesChart minutesChart = useCase.getMinutesChart(request);
        this.candleQueue = new LinkedList<>(minutesChart.getCandles());
    }

    @Override
    public Candle read() {
        return candleQueue.poll();
    }
}
