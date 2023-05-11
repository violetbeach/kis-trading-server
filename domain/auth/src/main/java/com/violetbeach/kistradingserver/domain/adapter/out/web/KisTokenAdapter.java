package com.violetbeach.kistradingserver.domain.adapter.out.web;

import com.violetbeach.kistradingserver.domain.application.port.out.IssueTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class KisTokenAdapter implements IssueTokenPort {
    private final KISTokenClient kisTokenClient;
    private final AuthKeys authKeys;
    @Override
    public void issueToken() {
        IssueTokenRequest request = initRequest();
        kisTokenClient.issueToken(request);
    }

    private IssueTokenRequest initRequest() {
        return new IssueTokenRequest(
                authKeys.grantType(),
                authKeys.appKey(),
                authKeys.secretKey()
        );
    }
}
