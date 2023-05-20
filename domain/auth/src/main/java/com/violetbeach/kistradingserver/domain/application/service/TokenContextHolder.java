package com.violetbeach.kistradingserver.domain.application.service;

import com.violetbeach.kistradingserver.domain.domain.Token;

public class TokenContextHolder {
    private static final ThreadLocal<Token> threadLocal = new ThreadLocal<>();

    public static Token getToken() {
        return threadLocal.get();
    }

    public static void setAuthToken(Token token) {
        threadLocal.set(token);
    }

}
