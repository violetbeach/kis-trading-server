package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
class DateTimeJobParameter {

    private LocalDateTime requestDateTime;

    @Value("#{jobParameters['requestDateTime']}")
    public void setBaseTime(String requestDateTime) {
        this.requestDateTime = LocalDateTime.parse(requestDateTime);
    }

}
