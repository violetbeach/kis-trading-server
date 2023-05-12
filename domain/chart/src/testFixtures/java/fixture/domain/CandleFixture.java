package fixture.domain;

import com.violetbeach.kistradingserver.domain.domain.Candle;
import com.violetbeach.kistradingserver.domain.domain.Money;
import com.violetbeach.kistradingserver.domain.domain.Volume;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public enum CandleFixture {
    삼성전자_분봉("005930", new Money(14400L), "123000", new Money(14600L), new Money(14200L), new Volume(12000));
    private final String stockCode;
    private final Money price;
    private final String baseTime;
    private final Money highPrice;
    private final Money lowPrice;
    private final Volume volume;

    CandleFixture(String stockCode, Money price, String baseTime, Money highPrice, Money lowPrice, Volume volume) {
        this.stockCode = stockCode;
        this.price = price;
        this.baseTime = baseTime;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.volume = volume;
    }

    public Candle getCandle() {
        return new Candle(
                this.stockCode,
                this.price,
                this.baseTime,
                this.highPrice,
                this.lowPrice,
                this.volume
        );
    }

}
