package fixture.domain;

import com.violetbeach.kistradingserver.domain.domain.Candle;
import com.violetbeach.kistradingserver.domain.domain.MinutesChart;

import java.util.List;

public enum MinutesChartFixture {

    삼성전자_차트(List.of(CandleFixture.삼성전자_분봉.getCandle()));

    private final List<Candle> candles;

    MinutesChartFixture(List<Candle> candles) {
        this.candles = candles;
    }

    public MinutesChart getChart() {
        return new MinutesChart(this.candles);
    }
}
