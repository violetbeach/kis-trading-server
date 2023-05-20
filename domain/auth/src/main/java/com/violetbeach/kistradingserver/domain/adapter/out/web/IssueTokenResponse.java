package com.violetbeach.kistradingserver.domain.adapter.out.web;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record IssueTokenResponse(
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("access_token_token_expired")
        LocalDateTime expireDate,
        @JsonProperty("token_type")
        String type
) {
}
