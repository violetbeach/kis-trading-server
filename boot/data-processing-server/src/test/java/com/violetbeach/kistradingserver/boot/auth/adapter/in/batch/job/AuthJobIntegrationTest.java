package com.violetbeach.kistradingserver.boot.auth.adapter.in.batch.job;

import com.violetbeach.kistradingserver.boot.support.batch.BaseBatchIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class AuthJobIntegrationTest extends BaseBatchIntegrationTest {

    @BeforeEach
    void setup(@Autowired Job authJob) {
        jobLauncherTestUtils.setJob(authJob);
    }

    @Test
    void JobExecution_STATUS_COMPLETE_확인() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }

}