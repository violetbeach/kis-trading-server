package com.violetbeach.kistradingserver.domain.domain;

import lombok.Getter;

@Getter
public class Money {
    private final long value;

    public Money(long value) {
        validMoney(value);
        this.value = value;
    }

    private void validMoney(long money) {
        if(money < 0) {
            throw new IllegalArgumentException("현금은 0원 이상이여야 합니다.");
        }
    }
}