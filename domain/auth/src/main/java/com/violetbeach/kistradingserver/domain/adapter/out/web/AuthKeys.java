package com.violetbeach.kistradingserver.domain.adapter.out.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("apis.kis.keys")
record AuthKeys (
       String grantType,
       String appKey,
       String secretKey
) {
}
