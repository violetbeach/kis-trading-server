package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import com.violetbeach.kistradingserver.domain.domain.Candle;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
class CandleEntityProcessor implements ItemProcessor<Candle, CandleVO> {

    private final String stockCode;

    @Override
    public CandleVO process(Candle candle) {
        CandleVO entity = toEntity(candle);
        return entity;
    }

    private CandleVO toEntity(Candle candle) {
        CandleVO entity = new CandleVO(
                stockCode,
                candle.getPrice(),
                candle.getBaseTime(),
                candle.getHighPrice(),
                candle.getLowPrice(),
                candle.getVolume()
        );
        return entity;
    }
}
