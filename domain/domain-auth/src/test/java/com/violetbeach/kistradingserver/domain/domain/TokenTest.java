package com.violetbeach.kistradingserver.domain.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class TokenTest {

    @Test
    @DisplayName("accessToken이 비어있으면 IllegalArgumentException를 반환한다.")
    public void withNullAccessToken_ItThrowsIllegalArgumentException() {
        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Token("", "Bearer", LocalDateTime.now());
        });
    }

    @Test
    @DisplayName("willExpireIn(N)은 N분 이내 만료되면 true를 반환한다.")
    public void testWillExpireIn() {
        // given
        LocalDateTime expirationTime = afterMinutes(30);
        Token token = new Token("abc123", "Bearer", expirationTime);

        // when & then
        assertThat(token.willExpireIn(10)).isFalse();
        assertThat(token.willExpireIn(30)).isTrue();
    }

    private LocalDateTime afterMinutes(int minutes) {
        return LocalDateTime.now().plusMinutes(minutes);
    }

}