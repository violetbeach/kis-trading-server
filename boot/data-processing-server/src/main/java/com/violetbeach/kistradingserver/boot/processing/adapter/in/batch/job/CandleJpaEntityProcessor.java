package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import com.violetbeach.kistradingserver.domain.domain.Candle;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
class CandleJpaEntityProcessor implements ItemProcessor<Candle, CandleVO> {

    @Override
    public CandleVO process(Candle candle) {
        CandleVO entity = toEntity(candle);
        return entity;
    }

    private CandleVO toEntity(Candle candle) {
        CandleVO entity = new CandleVO(
                candle.getPrice(),
                candle.getBaseTime(),
                candle.getHighPrice(),
                candle.getLowPrice(),
                candle.getVolume()
        );
        return entity;
    }
}
