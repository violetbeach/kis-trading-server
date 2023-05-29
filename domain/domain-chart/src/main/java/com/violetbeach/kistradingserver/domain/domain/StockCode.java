package com.violetbeach.kistradingserver.domain.domain;

import lombok.Getter;

@Getter
public class StockCode {
    private final String code;

    public StockCode(String stockCode) {
        validStockCode(stockCode);
        this.code = stockCode;
    }

    private void validStockCode(String stockCode) {
        if(stockCode.length() != 6) {
            throw new IllegalArgumentException("종목 코드는 6자리여야 합니다.");
        }
    }

}
