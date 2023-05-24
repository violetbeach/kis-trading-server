package com.violetbeach.kistradingserver.domain.adapter.out.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
public class GetMinutesChartCommand {
    @JsonProperty("FID_ETC_CLS_CODE")
    private final String FID_ETC_CLS_CODE = "";
    @JsonProperty("FID_COND_MRKT_DIV_CODE")
    private final String FID_COND_MRKT_DIV_CODE = "J";
    @JsonProperty("FID_INPUT_ISCD")
    private final String FID_INPUT_ISCD;
    @JsonProperty("FID_INPUT_HOUR_1")
    private final String FID_INPUT_HOUR_1;
    @JsonProperty("FID_PW_DATA_INCU_YN")
    private final String FID_PW_DATA_INCU_YN = "Y";

    GetMinutesChartCommand(String stockCode, LocalTime time) {
        this.FID_INPUT_ISCD = stockCode;
        this.FID_INPUT_HOUR_1 = time.format(DateTimeFormatter.ofPattern("HHmmss"));
    }
}
