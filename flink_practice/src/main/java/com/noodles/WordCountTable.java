package com.noodles;

import java.util.concurrent.ExecutionException;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.Expressions;
import org.apache.flink.table.api.Schema;
import org.apache.flink.table.api.TableDescriptor;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @description: Table 使用
 * @author: liuxian
 * @date: 2024-01-25 22:29
 */
public class WordCountTable {

    public static void main(String[] args) throws Exception {
        // 1, 构建流式执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);
        tableEnv.getConfig().set("parallelism.default", "1");

        // 2, 数据输入（数据输入表）
        tableEnv.createTemporaryTable("source", TableDescriptor.forConnector("datagen")
                .schema(Schema.newBuilder().column("word", DataTypes.STRING()).build())
                .option("rows-per-second", "1")
                .option("fields.word.kind", "random")
                .option("fields.word.length", "2").build());

        // 3, 数据输出（数据输出表)
        tableEnv.createTemporaryTable("sink", TableDescriptor.forConnector("print")
                .schema(Schema.newBuilder()
                        .column("word", DataTypes.STRING())
                        .column("counts", DataTypes.BIGINT()).build()).build());

        // 4, 数据处理（基于数据输入表、数据输出表进行业务处理）
        tableEnv.from("source")
                .groupBy(Expressions.$("word"))
                .select(Expressions.$("word"), Expressions.lit(1).count())
                .executeInsert("sink").await();

        // 5, 启动流式任务
        env.execute();
    }

}
