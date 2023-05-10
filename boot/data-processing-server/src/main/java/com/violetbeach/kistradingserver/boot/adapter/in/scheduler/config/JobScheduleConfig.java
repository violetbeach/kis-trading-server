package com.violetbeach.kistradingserver.boot.adapter.in.scheduler.config;

import com.violetbeach.kistradingserver.boot.adapter.in.scheduler.DataProcessScheduleJob;
import lombok.RequiredArgsConstructor;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import static org.quartz.JobBuilder.newJob;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class JobScheduleConfig {

    private final ApplicationContext context;

    @Bean
    public JobDetail jobDetail() {
        return newJob(DataProcessScheduleJob.class)
                .withIdentity("dataProcessJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInMinutes(1)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("dataProcessJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactory(Trigger trigger, JobDetail jobDetail, SpringBeanJobFactory jobFactory) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setTriggers(trigger);
        schedulerFactory.setJobDetails(jobDetail);
        schedulerFactory.setJobFactory(jobFactory);
        return schedulerFactory;
    }

    @Bean
    public SpringBeanJobFactory jobFactory() {
        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        jobFactory.setApplicationContext(context);
        return jobFactory;
    }
}