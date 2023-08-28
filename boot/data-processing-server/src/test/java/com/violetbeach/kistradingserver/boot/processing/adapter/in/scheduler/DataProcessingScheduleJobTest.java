package com.violetbeach.kistradingserver.boot.processing.adapter.in.scheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
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
    @DisplayName("JobLauncher.run()을 호출한다.")
    public void ItCallJobLauncherRun() throws Exception {
        // given
        JobExecutionContext jobExecutionContext = mock(JobExecutionContext.class);

        // when
        dataProcessingScheduleJob.executeInternal(jobExecutionContext);

        // then
        verify(jobLauncher, times(1)).run(eq(dataProcessingJob), any(JobParameters.class));
    }

    @Test
    @DisplayName("ExecutionContext가 이미 실행중이면 JobExecutionException을 발생한다.")
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

    @Nested
    class BaseDateTime {

        @Captor
        ArgumentCaptor<JobParameters> captor;

        @Test
        @DisplayName("30분 이전에 실행하면 BaseDateTime은 0분 0초가 된다.")
        public void withBefore30Minutes_BaseDateTimeIs0m0s() throws Exception {
            // given
            LocalDateTime mockNow = LocalDateTime.of(2022, 8, 10, 12, 1, 1);

            JobExecutionContext jobExecutionContext = mock(JobExecutionContext.class);

            // given & when
            try (MockedStatic<LocalDateTime> mockLocalDateTime = mockStatic(LocalDateTime.class)) {
                mockLocalDateTime.when(LocalDateTime::now).thenReturn(mockNow);

                dataProcessingScheduleJob.executeInternal(jobExecutionContext);
            }

            // then
            verify(jobLauncher, times(1)).run(eq(dataProcessingJob), captor.capture());
            JobParameters jobParams = captor.getValue();
            LocalDateTime baseDateTime = LocalDateTime.parse(jobParams.getString("baseDateTime"));
            assertAll(
                    () -> assertThat(baseDateTime.getMinute()).isEqualTo(0),
                    () -> assertThat(baseDateTime.getSecond()).isEqualTo(0)
            );
        }

        @Test
        @DisplayName("30분 이전에 실행하면 BaseDateTime은 0분 0초가 된다.")
        public void withAfter30Minutes_BaseDateTimeIs0m0s() throws Exception {
            // given
            LocalDateTime mockNow = LocalDateTime.of(2022, 8, 10, 12, 31, 1);

            JobExecutionContext jobExecutionContext = mock(JobExecutionContext.class);

            // given & when
            try (MockedStatic<LocalDateTime> mockLocalDateTime = mockStatic(LocalDateTime.class)) {
                mockLocalDateTime.when(LocalDateTime::now).thenReturn(mockNow);

                dataProcessingScheduleJob.executeInternal(jobExecutionContext);
            }

            // then
            verify(jobLauncher, times(1)).run(eq(dataProcessingJob), captor.capture());
            JobParameters jobParams = captor.getValue();
            LocalDateTime baseDateTime = LocalDateTime.parse(jobParams.getString("baseDateTime"));
            assertAll(
                    () -> assertThat(baseDateTime.getMinute()).isEqualTo(30),
                    () -> assertThat(baseDateTime.getSecond()).isEqualTo(0)
            );
        }

    }
}