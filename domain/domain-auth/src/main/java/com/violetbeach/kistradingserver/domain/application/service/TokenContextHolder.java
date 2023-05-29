package com.violetbeach.kistradingserver.domain.application.service;

import com.violetbeach.kistradingserver.domain.domain.Token;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TokenContextHolder {
    private static Token token;

    public static Token getToken() {
        return token;
    }

    public static void setAuthToken(Token token) {
        TokenContextHolder.token = token;
    }

    public static void clear() {
        token = null;
    }

}
