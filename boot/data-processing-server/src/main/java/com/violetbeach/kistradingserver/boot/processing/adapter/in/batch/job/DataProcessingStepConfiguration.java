package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import com.violetbeach.kistradingserver.domain.application.port.in.GetMinutesChartUseCase;
import com.violetbeach.kistradingserver.domain.domain.Candle;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
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
    private final ItemProcessor<Candle, CandleVO> candleJpaEntityProcessor;
    private final GetMinutesChartUseCase getMinutesChartUseCase;
    private final DataSource dataSource;
    private static final int chunkSize = 10;

    @Bean
    @JobScope
    public Step chartProcessingMasterStep() {
        return stepBuilderFactory.get("chartProcessingMasterStep")
                .partitioner("chartProcessingMasterStep", chartPartitioner)
                .step(chartProcessingSubStep())
                .gridSize(5)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Step chartProcessingSubStep() {
        return stepBuilderFactory.get("chartProcessingSubStep")
                .<Candle, CandleVO>chunk(chunkSize)
                .reader(candleItemReader(getMinutesChartUseCase, timeJobParameter(), null))
                .processor(candleJpaEntityProcessor)
                .writer(candleBatchItemWriter(null))
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
    @StepScope
    public ItemReader<Candle> candleItemReader(final GetMinutesChartUseCase useCase,
                                               final DateTimeJobParameter timeJobParameter,
                                               @Value("#{stepExecutionContext['stock_code']}") String stockCode) {
        return new CandleItemReader(useCase, stockCode, timeJobParameter);
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

    @Bean
    @StepScope
    public DateTimeJobParameter timeJobParameter() {
        return new DateTimeJobParameter();
    }

}
