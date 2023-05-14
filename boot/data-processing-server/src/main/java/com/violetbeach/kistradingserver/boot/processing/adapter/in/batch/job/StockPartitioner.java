package com.violetbeach.kistradingserver.boot.processing.adapter.in.batch.job;

import com.violetbeach.kistradingserver.boot.processing.domain.TargetStocks;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
class StockPartitioner implements Partitioner {

    private final TargetStocks targetStocks;

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> result = new HashMap<>();

        List<String> targetStockCodes = targetStocks.targetStockCodes();
        for (int i = 0; i < targetStockCodes.size(); i++) {
            ExecutionContext context = new ExecutionContext();
            context.put("stock_code", targetStockCodes.get(i));
            result.put("partition" + i, context);
        }
        return result;
    }
}
