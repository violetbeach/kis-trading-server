package com.violetbeach.kistradingserver.boot.config.feign;

import com.violetbeach.kistradingserver.domain.application.port.in.IssueTokenUseCase;
import com.violetbeach.kistradingserver.domain.application.service.TokenContextHolder;
import com.violetbeach.kistradingserver.domain.domain.Token;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.violetbeach.kistradingserver.domain.application.service.TokenContextHolder.*;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements RequestInterceptor {

    private final IssueTokenUseCase issueTokenUseCase;

    @Override
    public void apply(RequestTemplate template) {
        Token token = getToken();
        token = renewToken(token);
        template.header("Authorization", toHeader(token));
    }

    private Token renewToken(Token token) {
        if(Objects.isNull(token) || token.willExpireIn(20)) {
            return issueTokenUseCase.issueToken();
        }
        return token;
    }

    private String toHeader(Token token) {
        return String.join(" ", token.type(), token.accessToken());
    }
}
