package com.violetbeach.kistradingserver.boot.config.feign;

import com.violetbeach.kistradingserver.domain.application.port.in.IssueTokenUseCase;
import com.violetbeach.kistradingserver.domain.application.service.TokenContextHolder;
import com.violetbeach.kistradingserver.domain.domain.Token;
import feign.RequestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static com.violetbeach.kistradingserver.domain.application.service.TokenContextHolder.setAuthToken;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthInterceptorTest {
    @InjectMocks
    AuthInterceptor authInterceptor;
    @Mock
    IssueTokenUseCase issueTokenUseCase;
    @Mock
    private RequestTemplate requestTemplate;

    @BeforeEach
    void setUp() {
        authInterceptor = new AuthInterceptor(issueTokenUseCase);
        TokenContextHolder.clear();
    }

    @Test
    @DisplayName("발급한 토큰이 null이 아니고 만료되지 않았으면 요청 헤더에 Authorization을 세팅한다.")
    void withNotNullAndNotWillExpire_ItSetAuthorizationHeader() {
        // given
        Token token = 만료되지_않은_토큰();
        setAuthToken(token);

        // when
        authInterceptor.apply(requestTemplate);

        // then
        String expected = toHeaderValue(token);
        verify(requestTemplate, times(1))
                .header(eq("Authorization"), eq(expected));
    }

    @Test
    @DisplayName("발급한 토큰이 만료되었으면 새로 발급한 토큰을 Authorization 헤더에 세팅한다.")
    void withExpireToken_ItSetNewAuthorizationHeader() {
        // given
        setAuthToken(만료_토큰());
        Token token = 만료되지_않은_토큰();
        given(issueTokenUseCase.issueToken()).willReturn(token);

        // when
        authInterceptor.apply(requestTemplate);

        // then
        String expected = toHeaderValue(token);
        verify(requestTemplate, times(1))
                .header(eq("Authorization"), eq(expected));
    }

    @Test
    @DisplayName("토큰이 없으면 새로 발급한 토큰을 Authorization 헤더에 세팅한다.")
    void withTokenIsNull_ItSetNewAuthorizationHeader() {
        // given
        Token token = 만료되지_않은_토큰();
        given(issueTokenUseCase.issueToken()).willReturn(token);

        // when
        authInterceptor.apply(requestTemplate);

        // then
        String expected = toHeaderValue(token);
        verify(requestTemplate, times(1))
                .header(eq("Authorization"), eq(expected));
    }

    private Token 만료되지_않은_토큰() {
        return new Token("test1", "Bearer", LocalDateTime.now().plusDays(1));
    }

    private Token 만료_토큰() {
        return new Token("test1", "Bearer", LocalDateTime.now().minusMinutes(1));
    }

    private String toHeaderValue(Token token) {
        return String.join(" ", token.type(), token.accessToken());
    }
}