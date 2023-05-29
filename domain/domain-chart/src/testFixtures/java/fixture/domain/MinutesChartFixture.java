package fixture.domain;

import com.violetbeach.kistradingserver.domain.domain.Candle;
import com.violetbeach.kistradingserver.domain.domain.MinutesChart;
import com.violetbeach.kistradingserver.domain.domain.StockCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Accessors(fluent = true)
public enum MinutesChartFixture {

    삼성전자_차트("005930", List.of(CandleFixture.삼성전자_분봉.getCandle()));
    private final String stockCode;
    private final List<Candle> candles;

    MinutesChartFixture(String stockCode, List<Candle> candles) {
        this.stockCode = stockCode;
        this.candles = candles;
    }

    public MinutesChart getChart() {
        return new MinutesChart(
                new StockCode(this.stockCode),
                this.candles
        );
    }
}
