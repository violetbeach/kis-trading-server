package com.violetbeach.kistradingserver.boot.auth.adapter.in.batch.job;

import com.violetbeach.kistradingserver.domain.application.port.in.IssueTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class AuthJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final IssueTokenUseCase issueTokenUseCase;

    @Bean
    public Job authJob() {
        return jobBuilderFactory.get("authJob")
                .start(tokenIssueStep())
                .build();
    }

    @Bean
    public Step tokenIssueStep() {
        return stepBuilderFactory.get("tokenIssueStep")
                .tasklet(((stepContribution, chunkContext) -> {
                    issueTokenUseCase.issueToken();
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

}