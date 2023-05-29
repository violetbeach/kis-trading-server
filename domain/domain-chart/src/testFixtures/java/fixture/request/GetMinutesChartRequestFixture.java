package fixture.request;

import com.violetbeach.kistradingserver.domain.application.port.in.request.GetMinutesChartRequest;

import java.time.LocalTime;

import static fixture.domain.MinutesChartFixture.삼성전자_차트;

public enum GetMinutesChartRequestFixture {
    삼성전자_분봉_조회_요청(삼성전자_차트.stockCode(), LocalTime.of(12, 30, 0));

    private final String stockCode;
    private final LocalTime time;

    GetMinutesChartRequestFixture(String stockCode, LocalTime time) {
        this.stockCode = stockCode;
        this.time = time;
    }

    public GetMinutesChartRequest getRequest() {
        return new GetMinutesChartRequest(this.stockCode, this.time);
    }
}
