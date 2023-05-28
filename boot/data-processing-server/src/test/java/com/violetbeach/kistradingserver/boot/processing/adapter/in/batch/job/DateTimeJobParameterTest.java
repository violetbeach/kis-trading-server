package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DateTimeJobParameterTest {

    private DateTimeJobParameter jobParameter;

    @BeforeEach
    public void setup() {
        jobParameter = new DateTimeJobParameter();
    }

    @Test
    @DisplayName("(String) DateTime으로 LocalDateTime을 세팅한다.")
    public void setBaseTime() {
        // given
        String baseDateTime = "2023-05-28T10:15:30";

        // when
        jobParameter.setBaseDateTime(baseDateTime);

        // then
        LocalDateTime expectedDateTime = LocalDateTime.parse(baseDateTime);
        assertThat(jobParameter.getBaseDateTime()).isEqualTo(expectedDateTime);
    }
}