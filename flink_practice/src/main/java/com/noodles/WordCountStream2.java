package com.noodles;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @description: 简化版
 * @author: liuxian
 * @date: 2024-01-25 22:21
 */
public class WordCountStream2 {

    public static void main(String[] args) throws Exception {
        // 1, 构建流式执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        // 2, 数据输入
        DataStreamSource<String> source = env.socketTextStream("localhost", 9999);
        // 3, 数据处理
        SingleOutputStreamOperator<Tuple2<String, Integer>> result = source.flatMap(
                        (String value, Collector<String> out) -> {
                            String[] words = value.split(" ");
                            for (String word : words) {
                                out.collect(word);
                            }
                        }).returns(Types.STRING).map(value -> Tuple2.of(value, 1))
                .returns(Types.TUPLE(Types.STRING, Types.INT))
                .keyBy(value -> value.f0)
                .sum(1);
        // 4, 数据输出
        result.print();
        // 5, 启动流式任务
        env.execute();
    }
}
