package com.violetbeach.kistradingserver.domain.adapter.out.web;

import com.violetbeach.kistradingserver.domain.application.port.out.IssueTokenPort;
import com.violetbeach.kistradingserver.domain.application.service.TokenContextHolder;
import com.violetbeach.kistradingserver.domain.domain.Token;
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
        IssueTokenResponse response = kisTokenClient.issueToken(request);

        Token token = getToken(response);
        TokenContextHolder.setAuthToken(token);
    }

    private Token getToken(IssueTokenResponse response) {
        return new Token(
                response.accessToken(),
                response.type(),
                response.expireDate());
    }

    private IssueTokenRequest initRequest() {
        return new IssueTokenRequest(
                authKeys.grantType(),
                authKeys.appKey(),
                authKeys.secretKey()
        );
    }
}
