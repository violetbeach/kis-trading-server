package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import com.violetbeach.kistradingserver.domain.domain.Candle;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.LocalTime;

@Configuration
@RequiredArgsConstructor
class DataProcessingStepConfiguration {

    private final StepBuilderFactory stepBuilderFactory;
    private final StockPartitioner chartPartitioner;
    private final ItemReader<Candle> candleItemReader;
    private final ItemProcessor<Candle, CandleJpaEntity> candleJpaEntityProcessor;

    private static final int chunkSize = 10;

    @Bean
    public Step chartBeforeProcessingStep() {
        return stepBuilderFactory.get("chartBeforeProcessingStep")
                .partitioner(chartProcessingStep(null).getName(), chartPartitioner)
                .step(chartProcessingStep(null))
                .gridSize(5)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    @JobScope
    public Step chartProcessingStep(@Value("#{jobParameters[baseTime]}") LocalTime baseTime) {
        return stepBuilderFactory.get("chartProcessingStep")
                .<Candle, CandleJpaEntity>chunk(chunkSize)
                .reader(candleItemReader)
                .processor(candleJpaEntityProcessor)
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(3);
        taskExecutor.setMaxPoolSize(6);
        taskExecutor.setThreadNamePrefix("api-thread-");
        return taskExecutor;
    }

}
