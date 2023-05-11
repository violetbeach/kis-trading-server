package com.violetbeach.kistradingserver.domain.adapter.out.web;

import com.violetbeach.kistradingserver.domain.application.port.out.IssueTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class KisTokenAdapter implements IssueTokenPort {
    private final KISTokenClient kisTokenClient;

    @Override
    public void issueToken() {
        kisTokenClient.issueToken();
    }
}
