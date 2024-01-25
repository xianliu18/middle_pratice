package com.noodles;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @description: TODO
 * @author: liuxian
 * @date: 2024-01-25 09:12
 */
public class WordCountStream {
    public static void main(String[] args) throws Exception {
        // 1, 构建流式执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.STREAMING);
        env.setParallelism(1);
        // 2, 数据输入（数据源）
        // nc -lk 9999  本地开启 9999 端口
        DataStreamSource<String> source = env.socketTextStream("localhost", 9999);
        // 3, 数据处理
        SingleOutputStreamOperator<String> flatMapStream = source.flatMap(
                new FlatMapFunction<String, String>() {
                    @Override
                    public void flatMap(String value, Collector<String> collector)
                            throws Exception {
                        String[] words = value.split(" ");
                        for (String word : words) {
                            collector.collect(word);
                        }
                    }
                });
        // 使用 map 转换成 (单词, 1)
        SingleOutputStreamOperator<Tuple2<String, Integer>> mapStream = flatMapStream.map(
                new MapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public Tuple2<String, Integer> map(String s) throws Exception {
                        return Tuple2.of(s, 1);
                    }
                });
        // 使用 keyBy 进行单词分组
        KeyedStream<Tuple2<String, Integer>, String> keyedStream = mapStream.keyBy(
                new KeySelector<Tuple2<String, Integer>, String>() {
                    @Override
                    public String getKey(Tuple2<String, Integer> value)
                            throws Exception {
                        return value.f0;
                    }
                });
        // 使用 reduce 进行聚合操作
        // reduce
//        keyedStream.reduce(new ReduceFunction<Tuple2<String, Integer>>() {
//            @Override
//            public Tuple2<String, Integer> reduce(Tuple2<String, Integer> value1,
//                    Tuple2<String, Integer> value2) throws Exception {
//                return Tuple2.of(value1.f0, value1.f1 + value2.f1);
//            }
//        });
        // 使用 sum 进行聚合操作
        SingleOutputStreamOperator<Tuple2<String, Integer>> result = keyedStream.sum(1);
        // 4, 数据输出
        result.print();
        // 5, 启动流式任务
        env.execute();
    }
}
