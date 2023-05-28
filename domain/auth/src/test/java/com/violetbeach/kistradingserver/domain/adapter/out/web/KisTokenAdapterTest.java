package com.violetbeach.kistradingserver.domain.adapter.out.web;

import com.violetbeach.kistradingserver.domain.domain.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KisTokenAdapterTest {
    KisTokenAdapter adapter;
    @Mock
    private KISTokenClient kisTokenClient;
    AuthKeys authKeys;

    @BeforeEach
    void setUp() {
        authKeys = new AuthKeys("grantType", "appKey", "secretKey");
        adapter = new KisTokenAdapter(kisTokenClient, authKeys);
    }

    @Test
    @DisplayName("KisTokenClient의 issueToken()을 호출한다.")
    void ItCallKisTokenClient() {
        // given
        IssueTokenResponse response = 토큰_발급_응답();
        given(kisTokenClient.issueToken(any(IssueTokenRequest.class)))
                .willReturn(response);

        // when
        Token token = adapter.issueToken();

        // then
        verify(kisTokenClient, times(1)).issueToken(
                new IssueTokenRequest(authKeys.grantType(), authKeys.appKey(), authKeys.secretKey()));

        assertAll(
                () -> assertThat(token.accessToken()).isEqualTo(response.accessToken()),
                () -> assertThat(token.type()).isEqualTo(response.type()),
                () -> assertThat(token.expirationTime()).isEqualTo(response.expireDate())
        );
    }

    private IssueTokenResponse 토큰_발급_응답() {
        LocalDateTime current = LocalDateTime.now();
        return new IssueTokenResponse("token", current.plusDays(1), "Bearer");
    }
}