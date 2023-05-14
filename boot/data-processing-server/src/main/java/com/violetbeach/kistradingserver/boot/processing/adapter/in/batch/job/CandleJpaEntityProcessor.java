package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import com.violetbeach.kistradingserver.domain.domain.Candle;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
class CandleJpaEntityProcessor implements ItemProcessor<Candle, CandleJpaEntity> {

    @Override
    public CandleJpaEntity process(Candle candle) {
        CandleJpaEntity entity = toEntity(candle);
        return entity;
    }

    private CandleJpaEntity toEntity(Candle candle) {
        CandleJpaEntity entity = new CandleJpaEntity(
                candle.getPrice(),
                candle.getBaseTime(),
                candle.getHighPrice(),
                candle.getLowPrice(),
                candle.getVolume()
        );
        return entity;
    }
}
