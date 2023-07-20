package com.violetbeach.kistradingserver.domain.adapter.out.web;

import com.violetbeach.kistradingserver.domain.application.port.out.LoadMinutesChartRequest;
import com.violetbeach.kistradingserver.domain.domain.Candle;
import com.violetbeach.kistradingserver.domain.domain.MinutesChart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
	@InjectMocks
    KisChartAdapter adapter;
    @Mock
    KISChartClient kisChartClient;

    @Test
	@DisplayName("KISChartClient.getMinutesChart()를 호출해서 MinutesChart를 조회한다.")
    void ItLoadMinutesChart() {
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

		// Same candles with candleResponses
        List<Candle> actualCandles = actualChart.getCandles();
        IntStream.range(0, actualCandles.size())
                .forEach(i -> {
                    CandleResponse expectCandle = response.candleResponseList().get(i);
                    Candle actualCandle = actualCandles.get(i);

                    assertAll(
                            () -> assertThat(actualCandle.getPrice()).isEqualTo(expectCandle.price()),
                            () -> assertThat(actualCandle.getBaseDateTime()).isEqualTo(LocalDateTime.of(expectCandle.baseDate(), expectCandle.baseTime())),
                            () -> assertThat(actualCandle.getHighPrice()).isEqualTo(expectCandle.highPrice()),
                            () -> assertThat(actualCandle.getLowPrice()).isEqualTo(expectCandle.lowPrice()),
                            () -> assertThat(actualCandle.getVolume()).isEqualTo(expectCandle.volume())
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