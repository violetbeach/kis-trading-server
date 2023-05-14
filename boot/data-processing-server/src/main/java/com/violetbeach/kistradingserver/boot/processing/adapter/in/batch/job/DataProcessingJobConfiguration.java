package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
class DataProcessingJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job dataProcessingJob(Step chartProcessingStep) {
        return jobBuilderFactory.get("dataProcessingJob")
                .start(chartProcessingStep)
                .build();
    }

}