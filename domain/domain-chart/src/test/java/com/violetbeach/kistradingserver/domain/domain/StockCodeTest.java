package com.violetbeach.kistradingserver.domain.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StockCodeTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "ABCBC", "1234567"})
    @DisplayName("문자열 길이가 6이 아니면 IllegalArgumentException를 반환한다.")
    void withLengthIsNotSix_ItThrowIllegalArgumentException(String stockCode) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            StockCode code = new StockCode(stockCode);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABCDEF", "123456", "A1B2C3", "ABC123"})
    @DisplayName("문자열 길이가 6이면 생성자를 통과한다.")
    void withLengthIsSix_ItSameCodeAndValue(String stockCode) {
        StockCode code = new StockCode(stockCode);
        Assertions.assertEquals(stockCode, code.getCode());
    }
}