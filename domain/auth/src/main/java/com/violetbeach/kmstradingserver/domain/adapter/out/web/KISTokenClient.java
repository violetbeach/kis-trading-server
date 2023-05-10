package com.violetbeach.kmstradingserver.domain.adapter.out.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "kis-client", url = "${apis.kis.url}")
public interface KISTokenClient {

    @PostMapping("/oauth2/tokenP")
    void issueToken();

}
