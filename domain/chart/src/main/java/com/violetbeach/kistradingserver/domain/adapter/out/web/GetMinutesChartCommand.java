package com.violetbeach.kistradingserver.domain.adapter.out.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

class GetMinutesChartCommand {
    @JsonProperty("FID_ETC_CLS_CODE")
    private final String clsCode = "";
    @JsonProperty("FID_COND_MRKT_DIV_CODE")
    private final String marketDivCode = "J";
    @JsonProperty("FID_INPUT_ISCD")
    private final String StockCode;
    @JsonProperty("FID_INPUT_HOUR_1")
    @DateTimeFormat(pattern = "HHmmSS")
    private final LocalTime time;
    @JsonProperty("FID_PW_DATA_INCU_YN")
    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    private final Boolean IncludePWData = true;

    GetMinutesChartCommand(String stockCode, LocalTime time) {
        StockCode = stockCode;
        this.time = time;
    }
}
