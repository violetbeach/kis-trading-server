package com.violetbeach.kistradingserver.boot.processing.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;

@ConstructorBinding
@ConfigurationProperties("data-processing")
public record TargetStocks (
        List<String> targetStockCodes
) {
}

