package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
class DateTimeJobParameter {

    private LocalDateTime baseDateTime;

    @Value("#{jobParameters['baseDateTime']}")
    public void setBaseDateTime(String baseDateTime) {
        this.baseDateTime = LocalDateTime.parse(baseDateTime);
    }

}
