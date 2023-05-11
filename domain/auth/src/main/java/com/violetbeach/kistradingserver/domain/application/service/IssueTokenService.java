package com.violetbeach.kistradingserver.domain.application.service;

import com.violetbeach.kistradingserver.domain.application.port.in.IssueTokenUseCase;
import com.violetbeach.kistradingserver.domain.application.port.out.IssueTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class IssueTokenService implements IssueTokenUseCase {

    private final IssueTokenPort issueTokenPort;

    @Override
    public void issueToken() {
        issueTokenPort.issueToken();
    }

}
