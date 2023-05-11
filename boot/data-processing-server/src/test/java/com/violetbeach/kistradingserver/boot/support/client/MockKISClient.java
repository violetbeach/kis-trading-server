package com.violetbeach.kistradingserver.boot.support.client;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockKISClient {

    public static void setupIssueToken() {
        stubFor(post("/oauth2/tokenP")
                .willReturn(ok()));
    }

}
