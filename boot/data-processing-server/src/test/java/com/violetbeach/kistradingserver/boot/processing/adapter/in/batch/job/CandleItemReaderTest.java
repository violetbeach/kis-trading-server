package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import com.violetbeach.kistradingserver.domain.application.port.in.GetMinutesChartUseCase;
import com.violetbeach.kistradingserver.domain.domain.Candle;
import com.violetbeach.kistradingserver.domain.domain.MinutesChart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static fixture.domain.MinutesChartFixture.삼성전자_차트;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CandleItemReaderTest {
    CandleItemReader reader;
    @Mock
    GetMinutesChartUseCase useCase;
    MinutesChart minutesChart;

    @BeforeEach
    void setup() {
        minutesChart = 삼성전자_차트.getChart();
        given(useCase.getMinutesChart(any())).willReturn(minutesChart);

        DateTimeJobParameter timeJobParameter = mockParam();

        reader = new CandleItemReader(useCase, 삼성전자_차트.stockCode(), timeJobParameter);
    }

    @Test
    @DisplayName("UseCase로 조회한 Candle 하나를 반환한다.")
    void ItReturnCandle() {
        // when
        Candle candle = reader.read();

        // then
        Candle expected = minutesChart.getCandles().get(0);
        assertThat(candle).isEqualTo(expected);
    }

    @Test
    @DisplayName("Candle 개수를 초과해서 read()하면 null을 반환한다.")
    void withExceedCandleSize_ItReturnNull() {
        // given
        for(int i = 0; i < minutesChart.getCandlesSize(); i++) {
            reader.read();
        }

        // when
        Candle candle = reader.read();

        // then
        assertThat(candle).isNull();
    }

    DateTimeJobParameter mockParam() {
        DateTimeJobParameter timeJobParameter = mock(DateTimeJobParameter.class);
        given(timeJobParameter.getBaseDateTime()).willReturn(LocalDateTime.now());
        return timeJobParameter;
    }

}