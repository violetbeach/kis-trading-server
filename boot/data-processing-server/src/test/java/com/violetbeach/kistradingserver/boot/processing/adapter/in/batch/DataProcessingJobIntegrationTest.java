package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch;

import com.violetbeach.kistradingserver.boot.support.batch.BaseBatchIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DataProcessingJobIntegrationTest extends BaseBatchIntegrationTest {

    @BeforeEach
    void setup(@Autowired Job dataProcessingJob) {
        jobLauncherTestUtils.setJob(dataProcessingJob);
    }

    @Test
    void JobExecution_STATUS_COMPLETE_확인() throws Exception {
        // given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("baseDateTime", LocalDateTime.now().toString())
                .toJobParameters();

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        // then
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }

}