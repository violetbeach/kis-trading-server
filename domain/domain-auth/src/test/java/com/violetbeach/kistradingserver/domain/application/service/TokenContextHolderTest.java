package com.violetbeach.kistradingserver.domain.application.service;

import com.violetbeach.kistradingserver.domain.domain.Token;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.violetbeach.kistradingserver.domain.application.service.TokenContextHolder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TokenContextHolderTest {

    @Test
    @DisplayName("TokenContextHolder의 Token을 반환한다.")
    void getToken_ItReturnHoldersToken() {
        // given
        Token expected = mock(Token.class);
        setAuthToken(expected);

        // when
        Token token = getToken();

        // that
        assertThat(token).isEqualTo(expected);
    }

    @Test
    @DisplayName("TokenContextHolder의 Token을 null로 초기화한다.")
    void clear_ItSetTokenNull() {
        // given
        setAuthToken(mock(Token.class));

        // when
        clear();

        // that
        assertThat(getToken()).isNull();
    }

}