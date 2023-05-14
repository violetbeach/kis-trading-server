package com.violetbeach.kistradingserver.boot.processing.adapter.in.scheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataProcessingScheduleJobTest {
    DataProcessingScheduleJob dataProcessingScheduleJob;
    @Mock
    Job dataProcessingJob;
    @Mock
    JobLauncher jobLauncher;

    @BeforeEach
    void setup() {
        dataProcessingScheduleJob = new DataProcessingScheduleJob(dataProcessingJob, jobLauncher);
    }

    @Test
    public void ItCallJobLauncherRun() throws Exception {
        // given
        JobExecutionContext jobExecutionContext = mock(JobExecutionContext.class);

        // when
        dataProcessingScheduleJob.executeInternal(jobExecutionContext);

        // then
        verify(jobLauncher, times(1)).run(eq(dataProcessingJob), any(JobParameters.class));
    }

    @Test
    public void withBatchJobException_ItThrowsJobExecution() throws Exception {
        // given
        JobExecutionContext jobExecutionContext = mock(JobExecutionContext.class);
        given(jobLauncher.run(eq(dataProcessingJob), any(JobParameters.class)))
                .willThrow(new JobExecutionAlreadyRunningException(""));

        // when & then
        assertThrows(JobExecutionException.class,
                () -> dataProcessingScheduleJob.executeInternal(jobExecutionContext)
        );
    }
}