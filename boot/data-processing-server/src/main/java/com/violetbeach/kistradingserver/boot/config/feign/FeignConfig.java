package com.violetbeach.kistradingserver.boot.config.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.violetbeach.kistradingserver")
public class FeignConfig {

}
