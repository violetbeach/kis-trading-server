package com.violetbeach.kistradingserver.domain.adapter.out.web;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

record GetMinutesChartResponse (
        @JsonProperty("output2")
        List<CandleResponse> candleResponseList
) {
}
