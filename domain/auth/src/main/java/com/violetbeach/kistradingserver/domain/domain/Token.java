package com.violetbeach.kistradingserver.domain.domain;

import java.time.LocalDateTime;


public record Token (
        String accessToken,
        String type,
        LocalDateTime expirationTime
) {
    public Token {
        validTokenNotEmpty(accessToken);
    }

    public boolean willExpireIn(int minutes) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime AfterMinutes = now.plusMinutes(minutes);
        return expirationTime.isBefore(AfterMinutes);
    }

    private void validTokenNotEmpty(String token) {
        if (token.length() <= 0) {
            throw new IllegalArgumentException("토큰은 비어있을 수 없습니다.");
        }
    }
}