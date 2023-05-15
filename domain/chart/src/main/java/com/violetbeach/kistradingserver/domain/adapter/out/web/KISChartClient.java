package com.violetbeach.kistradingserver.domain.adapter.out.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kis-auth-client", url = "${apis.kis.url}")
interface KISChartClient {

    @PostMapping("/uapi/domestic-stock/v1/quotations/inquire-time-itemchartprice")
    GetMinutesChartResponse getMinutesChart(@RequestParam GetMinutesChartCommand issueTokenRequest);

}
