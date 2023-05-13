package com.violetbeach.kistradingserver.domain.adapter.out.web;

import com.violetbeach.kistradingserver.domain.application.port.out.LoadMinutesChartRequest;
import com.violetbeach.kistradingserver.domain.domain.Candle;
import com.violetbeach.kistradingserver.domain.domain.MinutesChart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.IntStream;

import static fixture.request.LoadMinutesChartRequestFixture.삼성전자_분봉_조회_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class KisChartAdapterTest {
    private KisChartAdapter adapter;
    @Mock
    private KISChartClient kisChartClient;

    @BeforeEach
    void setUp() {
        adapter = new KisChartAdapter(kisChartClient);
    }

    @Test
    void loadMinutesChart() {
        // given
        LoadMinutesChartRequest 분봉_조회_요청 = 삼성전자_분봉_조회_요청.getRequest();
        GetMinutesChartResponse response = mockResponse();

        given(kisChartClient.getMinutesChart(any(GetMinutesChartCommand.class)))
                .willReturn(response);

        // when
        MinutesChart actualChart = adapter.loadMinutesChart(분봉_조회_요청);

        // then
        assertAll(
                () -> assertThat(actualChart.getStockCode()).isEqualTo(분봉_조회_요청.stockCode()),
                () -> assertThat(actualChart.getCandles()).hasSameSizeAs(response.candleResponseList())
        );

        List<Candle> actualCandles = actualChart.getCandles();
        IntStream.range(0, actualCandles.size())
                .forEach(i -> {
                    CandleResponse expectCandle = response.candleResponseList().get(i);
                    Candle actualCandle = actualCandles.get(i);

                    assertAll(
                            () -> assertThat(actualCandle.getPrice()).isEqualTo(expectCandle.price()),
                            () -> assertThat(actualCandle.getBaseTime()).isEqualTo(expectCandle.baseTime()),
                            () -> assertThat(actualCandle.getHighPrice()).isEqualTo(expectCandle.highPrice()),
                            () -> assertThat(actualCandle.getLowPrice()).isEqualTo(expectCandle.lowPrice()),
                            () -> assertThat(actualCandle.getVolume().value()).isEqualTo(expectCandle.volume())
                    );
                });
    }

    private List<CandleResponse> mockCandleInfo() {
        return List.of(
                new CandleResponse(
                        360_000L,
                        LocalDate.of(2020, 2, 23),
                        LocalTime.of(12, 30, 0),
                        368_000L,
                        358_000L,
                        30_000_000L
                )
        );
    }

    private GetMinutesChartResponse mockResponse() {
        return new GetMinutesChartResponse(
                mockCandleInfo()
        );
    }

}