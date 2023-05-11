package com.violetbeach.kistradingserver.boot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class QuartzConfig {

    private final ApplicationContext context;

    @Bean
    public SpringBeanJobFactory jobFactory() {
        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        jobFactory.setApplicationContext(context);
        return jobFactory;
    }

}
