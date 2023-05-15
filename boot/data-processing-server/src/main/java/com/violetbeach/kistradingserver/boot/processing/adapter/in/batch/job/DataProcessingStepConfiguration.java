package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import com.violetbeach.kistradingserver.domain.domain.Candle;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
class DataProcessingStepConfiguration {

    private final StepBuilderFactory stepBuilderFactory;
    private final StockPartitioner chartPartitioner;
    private final ItemReader<Candle> candleItemReader;
    private final ItemProcessor<Candle, CandleVO> candleJpaEntityProcessor;
    private final DataSource dataSource;
    private static final int chunkSize = 10;

    @Bean
    public Step chartBeforeProcessingStep() {
        return stepBuilderFactory.get("chartBeforeProcessingStep")
                .partitioner("chartProcessingStep", chartPartitioner)
                .step(chartProcessingStep())
                .gridSize(5)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    @JobScope
    public Step chartProcessingStep() {
        return stepBuilderFactory.get("chartProcessingStep")
                .<Candle, CandleVO>chunk(chunkSize)
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

    @Bean
    @JobScope
    public JdbcBatchItemWriter<CandleVO> candleBatchItemWriter(@Value("#{jobParameters['stock_code']}") String stockCode) {
        return new JdbcBatchItemWriterBuilder<CandleVO>()
                .dataSource(dataSource)
                .sql("""
                    INSERT INTO candle(stick_code, price, high_price, low_price, volume, base_time)
                        values(:stock_code, :price, :baseTime, :highPrice, :lowPrice, :volume, :baseTime)
                    """)
                .build();
    }

}
