package com.violetbeach.kistradingserver.domain.domain;

import lombok.Getter;

@Getter
public class Volume {
    private final long value;

    public Volume(long value) {
        validMoney(value);
        this.value = value;
    }

    private void validMoney(long money) {
        if(money < 0) {
            throw new IllegalArgumentException("거래량은 0 이상이여야 합니다.");
        }
    }
}