package com.violetbeach.kistradingserver.domain.application.service;

import com.violetbeach.kistradingserver.domain.application.port.in.request.GetMinutesChartRequest;
import com.violetbeach.kistradingserver.domain.application.port.out.LoadMinutesChartPort;
import com.violetbeach.kistradingserver.domain.application.port.out.LoadMinutesChartRequest;
import com.violetbeach.kistradingserver.domain.domain.MinutesChart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static fixture.domain.MinutesChartFixture.삼성전자_차트;
import static fixture.request.GetMinutesChartRequestFixture.삼성전자_분봉_조회_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MinutesChartServiceTest {

	@InjectMocks
    MinutesChartService service;
    @Mock
    LoadMinutesChartPort loadMinutesChartPort;

    @Test
    void getMinutesChart() {
        // given
        GetMinutesChartRequest 조회_요청 = 삼성전자_분봉_조회_요청.getRequest();
        MinutesChart 차트 = 삼성전자_차트.getChart();
        given(loadMinutesChartPort.loadMinutesChart(포트_리퀘스트_생성(조회_요청)))
                .willReturn(차트);

        // when
        MinutesChart result = service.getMinutesChart(조회_요청);

        // then
        assertThat(result).isEqualTo(차트);
    }

    private LoadMinutesChartRequest 포트_리퀘스트_생성(GetMinutesChartRequest 조회_요청) {
        return new LoadMinutesChartRequest(
                조회_요청.stockCode(),
                조회_요청.time()
        );
    }

}