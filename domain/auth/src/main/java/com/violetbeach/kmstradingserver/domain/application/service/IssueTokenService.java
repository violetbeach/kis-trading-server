package com.violetbeach.kmstradingserver.domain.application.service;

import com.violetbeach.kmstradingserver.domain.application.port.in.IssueTokenUseCase;
import com.violetbeach.kmstradingserver.domain.application.port.out.IssueTokenPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class IssueTokenService implements IssueTokenUseCase {

    private final IssueTokenPort issueTokenPort;

    public void issueToken() {
        issueTokenPort.issueToken();
    }

}
