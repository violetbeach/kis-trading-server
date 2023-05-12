package fixture.request;

import com.violetbeach.kistradingserver.domain.application.port.in.request.GetMinutesChartRequest;

import static fixture.domain.CandleFixture.삼성전자_분봉;

public enum GetMinutesChartRequestFixture {
    삼성전자_분봉_조회_요청(삼성전자_분봉.stockCode(), "123000");

    private final String stockCode;
    private final String time;

    GetMinutesChartRequestFixture(String stockCode, String time) {
        this.stockCode = stockCode;
        this.time = time;
    }

    public GetMinutesChartRequest getRequest() {
        return new GetMinutesChartRequest(this.stockCode, this.time);
    }
}
