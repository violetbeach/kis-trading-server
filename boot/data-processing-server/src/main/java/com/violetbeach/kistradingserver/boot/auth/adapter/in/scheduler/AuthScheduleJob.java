package com.violetbeach.kistradingserver.boot.auth.adapter.in.scheduler;

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

import java.util.UUID;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class AuthScheduleJob extends QuartzJobBean {
    private Job dataProcessJob;
    private JobLauncher jobLauncher;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("tmp", UUID.randomUUID().toString())
                .toJobParameters();
        try {
            jobLauncher.run(dataProcessJob, jobParameters);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
