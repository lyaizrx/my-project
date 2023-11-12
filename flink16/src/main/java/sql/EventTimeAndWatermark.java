package sql;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * ClassName: EventTimeAndWatermarkDemo9
 * Package: sql
 * Description:
 *
 * @Author ly
 * @Create 2022/12/20 16:57
 * @Version 1.0
 */
public class EventTimeAndWatermark {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        EnvironmentSettings settings = EnvironmentSettings.inStreamingMode();
        StreamTableEnvironment tenv = StreamTableEnvironment.create(env, settings);

        /**
         * 只有  TIMESTAMP 或  TIMESTAMP_LTZ 类型的字段可以被声明为rowtime（事件时间属性）
         */
        tenv.executeSql(
                " create table t_events(                                          "
                        + "   guid int,                                                     "
                        + "   eventId string,                                               "
                        /*+ "   eventTime timestamp(3),                                     "*/
                        + "   eventTime bigint,                                             "
                        + "   pageId  string,                                               "
                        + "   pt AS proctime(),                                             "  // 利用一个表达式字段，来声明 processing time属性
                        + "   rt as to_timestamp_ltz(eventTime,3),                          "
                        + "   watermark for rt  as rt - interval '0.001' second             "  // 用watermark for xxx，来将一个已定义的TIMESTAMP/TIMESTAMP_LTZ字段声明成 eventTime属性及指定watermark策略
                        + " )                                                               "
                        + " with (                                                          "
                        + "   'connector' = 'kafka',                                        "
                        + "   'topic' = 'doit30-events2',                                   "
                        + "   'properties.bootstrap.servers' = 'doitedu:9092',              "
                        + "   'properties.group.id' = 'g1',                                 "
                        + "   'scan.startup.mode' = 'earliest-offset',                      "
                        + "   'format' = 'json',                                            "
                        + "   'json.fail-on-missing-field' = 'false',                       "
                        + "   'json.ignore-parse-errors' = 'true'                           "
                        + " )                                                               "
        );

        tenv.executeSql("desc t_events")/*.print()*/;
        tenv.executeSql("select guid,eventId,eventTime,pageId,pt,rt,CURRENT_WATERMARK(rt) as wm from t_events").print();


    }
}
