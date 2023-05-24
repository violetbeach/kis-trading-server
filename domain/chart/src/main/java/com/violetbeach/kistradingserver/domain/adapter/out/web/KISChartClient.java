package com.violetbeach.kistradingserver.domain.adapter.out.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "kis-auth-client", url = "${apis.kis.url}")
interface KISChartClient {

    @GetMapping("/uapi/domestic-stock/v1/quotations/inquire-time-itemchartprice")
    GetMinutesChartResponse getMinutesChart(@SpringQueryMap GetMinutesChartCommand command);

}
