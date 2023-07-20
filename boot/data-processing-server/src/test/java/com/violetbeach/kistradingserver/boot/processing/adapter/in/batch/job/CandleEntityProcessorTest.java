package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import com.violetbeach.kistradingserver.domain.domain.Candle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fixture.domain.CandleFixture.삼성전자_분봉;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CandleEntityProcessorTest {

    CandleEntityProcessor processor = new CandleEntityProcessor("000021");

    @Test
    @DisplayName("Candle과 StockCode를 조합하여 CandleVO를 반환한다.")
    void ItReturnCandleVO() {
        // given
        Candle expected = 삼성전자_분봉.getCandle();

        // when
        CandleVO candleVO = processor.process(expected);

        assertAll(
                () -> assertThat(candleVO.stockCode()).isEqualTo("000021"),
                () -> assertThat(candleVO.price()).isEqualTo(expected.getPrice()),
                () -> assertThat(candleVO.highPrice()).isEqualTo(expected.getHighPrice()),
                () -> assertThat(candleVO.lowPrice()).isEqualTo(expected.getLowPrice()),
                () -> assertThat(candleVO.volume()).isEqualTo(expected.getVolume()),
                () -> assertThat(candleVO.baseDateTime()).isEqualTo(expected.getBaseDateTime())
        );


    }

}