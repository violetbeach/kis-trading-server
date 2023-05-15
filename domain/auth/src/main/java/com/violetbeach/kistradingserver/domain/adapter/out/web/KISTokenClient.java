package com.violetbeach.kistradingserver.domain.adapter.out.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "kis-chart-client", url = "${apis.kis.url}")
interface KISTokenClient {

    @PostMapping("/oauth2/tokenP")
    void issueToken(@RequestBody IssueTokenRequest issueTokenRequest);

}
