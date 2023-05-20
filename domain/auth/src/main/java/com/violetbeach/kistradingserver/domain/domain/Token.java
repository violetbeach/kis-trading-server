package com.violetbeach.kistradingserver.domain.domain;

public class Token {
    private final String token;
    private final String type;

    public Token(String token, String type) {
        validTokenNotEmpty(token);
        this.token = token;
        this.type = type;
    }

    private void validTokenNotEmpty(String token) {
        if(token.length() <= 0) {
            throw new IllegalArgumentException("토큰은 비어있을 수 없습니다.");
        }
    }
}
