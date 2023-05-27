package com.violetbeach.kistradingserver.boot.processing.adapter.in.scheduler;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;

@Configuration
class DataProcessingJobScheduleConfig {

    private static final String cronExp = "0 32/30 9-17 ? * MON-FRI";

    @Bean
    public JobDetail dataProcessingJobDetail() {
        return newJob(DataProcessingScheduleJob.class)
                .withIdentity("dataProcessingJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger dataProcessingJobTrigger(JobDetail dataProcessingJobDetail) {
        CronScheduleBuilder cronScheduleBuilder = cronSchedule(cronExp);

        return TriggerBuilder.newTrigger()
                .forJob(dataProcessingJobDetail)
                .withIdentity("dataProcessingJobTrigger")
                .withSchedule(cronScheduleBuilder)
                .build();
    }

    @Bean
    public SchedulerFactoryBean dataProcessingJobSchedulerFactory(Trigger dataProcessingJobTrigger, JobDetail dataProcessingJobDetail, SpringBeanJobFactory jobFactory) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setTriggers(dataProcessingJobTrigger);
        schedulerFactory.setJobDetails(dataProcessingJobDetail);
        schedulerFactory.setJobFactory(jobFactory);
        return schedulerFactory;
    }

}