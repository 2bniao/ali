package com.mt.shardding;

import java.util.Collection;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

public class SingleKeyModuloTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<String> {

    @Override
    public Collection<String> doBetweenSharding(Collection<String> arg0, ShardingValue<String> arg1) {
        return null;
    }

    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<String> shardingValue) {
        Class<? extends ShardingValue> value = shardingValue.getClass();

        for (String each : availableTargetNames) {

        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> arg0, ShardingValue<String> arg1) {
        return null;
    }
}
