package fixture.domain;

import com.violetbeach.kistradingserver.domain.domain.Candle;
import com.violetbeach.kistradingserver.domain.domain.Money;
import com.violetbeach.kistradingserver.domain.domain.Volume;

import java.time.LocalDateTime;
import java.time.LocalTime;

public enum CandleFixture {
    삼성전자_분봉(new Money(14400L), LocalDateTime.of(2022, 12, 1, 12, 30, 0), new Money(14600L), new Money(14200L), new Volume(12000));
    private final Money price;
    private final LocalDateTime baseDateTime;
    private final Money highPrice;
    private final Money lowPrice;
    private final Volume volume;

    CandleFixture(Money price, LocalDateTime baseDateTime, Money highPrice, Money lowPrice, Volume volume) {
        this.price = price;
        this.baseDateTime = baseDateTime;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.volume = volume;
    }

    public Candle getCandle() {
        return new Candle(
                this.price,
                this.baseDateTime,
                this.highPrice,
                this.lowPrice,
                this.volume
        );
    }

}
