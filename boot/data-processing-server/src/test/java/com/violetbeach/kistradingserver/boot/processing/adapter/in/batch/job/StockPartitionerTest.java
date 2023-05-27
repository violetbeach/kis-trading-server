package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import com.violetbeach.kistradingserver.boot.processing.application.service.TargetStocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.item.ExecutionContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StockPartitionerTest {

    StockPartitioner stockPartitioner;

    @BeforeEach
    void setup() {
        TargetStocks targetStocks = new TargetStocks(List.of("000001", "000002", "000003"));
        stockPartitioner = new StockPartitioner(targetStocks);
    }

    @Test
    void stockSize에_맞게_파티션이_분할된다() {
        // when
        var contextMap = stockPartitioner.partition(3);

        // then
        ExecutionContext partition1 = contextMap.get("partition0");
        assertThat(partition1.get("stock_code")).isEqualTo("000001");

        ExecutionContext partition3 = contextMap.get("partition2");
        assertThat(partition3.get("stock_code")).isEqualTo("000003");
    }

}