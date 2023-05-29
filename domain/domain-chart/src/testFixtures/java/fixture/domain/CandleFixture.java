package fixture.domain;

import com.violetbeach.kistradingserver.domain.domain.Candle;
import com.violetbeach.kistradingserver.domain.domain.Money;
import com.violetbeach.kistradingserver.domain.domain.Volume;

import java.time.LocalTime;

public enum CandleFixture {
    삼성전자_분봉(new Money(14400L), LocalTime.of(12, 30, 0), new Money(14600L), new Money(14200L), new Volume(12000));
    private final Money price;
    private final LocalTime baseTime;
    private final Money highPrice;
    private final Money lowPrice;
    private final Volume volume;

    CandleFixture(Money price, LocalTime baseTime, Money highPrice, Money lowPrice, Volume volume) {
        this.price = price;
        this.baseTime = baseTime;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.volume = volume;
    }

    public Candle getCandle() {
        return new Candle(
                this.price,
                this.baseTime,
                this.highPrice,
                this.lowPrice,
                this.volume
        );
    }

}
