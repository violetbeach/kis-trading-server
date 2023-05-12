package com.violetbeach.kistradingserver.domain.adapter.out.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KisTokenAdapterTest {
    private KisTokenAdapter adapter;
    @Mock
    private KISTokenClient kisTokenClient;

    @BeforeEach
    void setUp() {
        AuthKeys authKeys = new AuthKeys("grantType", "appKey", "secretKey");
        adapter = new KisTokenAdapter(kisTokenClient, authKeys);
    }

    @Test
    void testIssueToken() {
        // given
        doNothing().when(kisTokenClient).issueToken(any(IssueTokenRequest.class));

        // when
        adapter.issueToken();

        // then
        verify(kisTokenClient, times(1)).issueToken(
                new IssueTokenRequest("grantType", "appKey", "secretKey")
        );
    }
}