package com.violetbeach.kistradingserver.boot.support.client;

import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.http.HttpStatus.OK;

public class MockKISClient {

    public static void setupIssueToken() {
        stubFor(post("/oauth2/tokenP")
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("payload/issue-token-response.json")));
    }

    public static void setupGetMinutesChart() {
        stubFor(get(urlPathEqualTo("/uapi/domestic-stock/v1/quotations/inquire-time-itemchartprice"))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("payload/get-minutes-chart-response.json")));
    }

}
