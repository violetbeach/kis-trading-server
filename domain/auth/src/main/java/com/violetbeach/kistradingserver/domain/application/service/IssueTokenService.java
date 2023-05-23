package com.violetbeach.kistradingserver.domain.application.service;

import com.violetbeach.kistradingserver.domain.application.port.in.IssueTokenUseCase;
import com.violetbeach.kistradingserver.domain.application.port.out.IssueTokenPort;
import com.violetbeach.kistradingserver.domain.domain.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class IssueTokenService implements IssueTokenUseCase {

    private final IssueTokenPort issueTokenPort;

    @Override
    public Token issueToken() {
        Token token = issueTokenPort.issueToken();
        TokenContextHolder.setAuthToken(token);
        return token;
    }

}
