package com.violetbeach.kistradingserver.boot.processing.adapter.in.scheduler;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import static org.quartz.JobBuilder.newJob;

@Configuration
class DataProcessingJobScheduleConfig {

    @Bean
    public JobDetail dataProcessingJobDetail() {
        return newJob(DataProcessingScheduleJob.class)
                .withIdentity("dataProcessingJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger dataProcessingJobTrigger(JobDetail dataProcessingJobDetail) {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInMinutes(30)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(dataProcessingJobDetail)
                .withIdentity("dataProcessingJobTrigger")
                .withSchedule(scheduleBuilder)
                .startAt(DateBuilder.todayAt(8, 10, 0))
                .endAt(DateBuilder.todayAt(18, 10, 0))
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