package com.violetbeach.kmstradingserver.domain.adapter.out.web;

import com.violetbeach.kmstradingserver.domain.application.port.out.IssueTokenPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class KisTokenAdapter implements IssueTokenPort {
    private final KISTokenClient kisTokenClient;
    
    @Override
    public void issueToken() {
        kisTokenClient.issueToken();
    }
}
