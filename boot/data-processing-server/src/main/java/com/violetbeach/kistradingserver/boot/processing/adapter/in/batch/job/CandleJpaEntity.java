package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class CandleJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;
    private LocalTime baseTime;
    private Long highPrice;
    private Long lowPrice;
    private Long volume;

    public CandleJpaEntity(Long price, LocalTime baseTime, Long highPrice, Long lowPrice, Long volume) {
        this.price = price;
        this.baseTime = baseTime;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.volume = volume;
    }

}
