package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
class TimeJobParameter {

    private LocalTime baseTime;

    @Value("#{jobParameters['baseTime']}")
    public void setBaseTime(String baseTime) {
        this.baseTime = LocalTime.parse(baseTime);
    }

}
