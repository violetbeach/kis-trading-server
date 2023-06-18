package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CandleBatchItemWriterTest {
    CandleBatchItemWriter writer;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setup(@Autowired DataSource dataSource) {
        writer = new CandleBatchItemWriter(dataSource);
        writer.afterPropertiesSet();
    }

    @Test
    @DisplayName("Candle 리스트를 DB에 저장한다.")
    void write() throws Exception {
        // given
        List<CandleVO> candles = generateCandleVO(10);

        // when
        writer.write(candles);

        // then
        List<CandleVO> results = queryCandles();
        assertThat(results).hasSize(10);

        assertThat(results)
                .usingRecursiveComparison()
                .isEqualTo(candles);
    }

    private List<CandleVO> queryCandles() {
        return jdbcTemplate.query("SELECT * FROM candle", (rs, rowNum) -> new CandleVO(
                rs.getString("stock_code"),
                rs.getLong("price"),
                rs.getTimestamp("base_date_time").toLocalDateTime(),
                rs.getLong("high_price"),
                rs.getLong("low_price"),
                rs.getLong("volume")
        ));
    }

    private List<CandleVO> generateCandleVO(int count) {
        List<CandleVO> candles = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            CandleVO candle = new CandleVO(
                    "000001",
                    50000L,
                    LocalDateTime.of(2022, 11, 1, 12, 30, i),
                    31000L,
                    29000L,
                    i * 10000L
            );
            candles.add(candle);
        }
        return candles;
    }

}