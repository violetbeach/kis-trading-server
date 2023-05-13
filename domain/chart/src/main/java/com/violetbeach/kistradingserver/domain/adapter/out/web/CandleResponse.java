package com.violetbeach.kistradingserver.domain.adapter.out.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

record CandleResponse(
        @JsonProperty("stck_prpr")
        Long price,
        @JsonProperty("stck_bsop_date")
        @DateTimeFormat(pattern = "yyyyMMdd")
        LocalDate baseDate,
        @JsonProperty("stck_cntg_hour")
        @DateTimeFormat(pattern = "HHmmSS")
        LocalTime baseTime,
        @JsonProperty("stck_hgpr")
        Long highPrice,
        @JsonProperty("stck_lwpr")
        Long lowPrice,
        @JsonProperty("cntg_vol")
        Long volume
) {
}
