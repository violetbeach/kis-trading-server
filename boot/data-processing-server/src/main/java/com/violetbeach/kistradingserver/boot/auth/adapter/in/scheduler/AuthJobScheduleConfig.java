package com.violetbeach.kistradingserver.boot.auth.adapter.in.scheduler;

import com.violetbeach.kistradingserver.boot.auth.adapter.in.scheduler.AuthScheduleJob;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import static org.quartz.JobBuilder.newJob;

@Configuration
class AuthJobScheduleConfig {

    @Bean
    public JobDetail authJobDetail() {
        return newJob(AuthScheduleJob.class)
                .withIdentity("authJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger authJobTrigger(JobDetail jobDetail) {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInHours(6)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("authJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactory(Trigger authJobTrigger, JobDetail authJobDetail, SpringBeanJobFactory jobFactory) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setTriggers(authJobTrigger);
        schedulerFactory.setJobDetails(authJobDetail);
        schedulerFactory.setJobFactory(jobFactory);
        return schedulerFactory;
    }

}