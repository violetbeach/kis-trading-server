package com.violetbeach.kistradingserver.boot.processing.adapter.in.scheduler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@AllArgsConstructor
class DataProcessingScheduleJob extends QuartzJobBean {
    private Job dataProcessingJob;
    private JobLauncher jobLauncher;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestDate", LocalDateTime.now().toString())
                .toJobParameters();
        try {
            jobLauncher.run(dataProcessingJob, jobParameters);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
