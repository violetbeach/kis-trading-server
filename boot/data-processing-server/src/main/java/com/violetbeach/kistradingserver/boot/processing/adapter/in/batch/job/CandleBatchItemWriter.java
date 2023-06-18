package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import javax.sql.DataSource;

public class CandleBatchItemWriter extends JdbcBatchItemWriter<CandleVO> {

    public CandleBatchItemWriter(DataSource dataSource) {
        setDataSource(dataSource);
        setSql("""
                INSERT INTO candle(stock_code, price, high_price, low_price, volume, base_date_time)
                values(:stockCode, :price, :highPrice, :lowPrice, :volume, :baseDateTime)
               """);
        setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
    }

}
