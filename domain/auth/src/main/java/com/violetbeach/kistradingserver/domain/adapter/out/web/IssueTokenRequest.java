package com.violetbeach.kistradingserver.domain.adapter.out.web;

import com.fasterxml.jackson.annotation.JsonProperty;

record IssueTokenRequest(
        @JsonProperty("grant_type")
        String grantType,
        @JsonProperty("appkey")
        String appKey,
        @JsonProperty("appsecret")
        String secretKey
) {
}
