package sql;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName: FlinkSqlTest
 * Package: flinksql
 * Description:
 *
 * @Author ly
 * @Create 2022/12/18 22:28
 * @Version 1.0
 */
public class FlinkSqlTest {
   private static final Logger LOG = LoggerFactory.getLogger(FlinkSqlTest.class);

   public static void main(String[] args) {
      /*
       * 创建table执行环境 方式1
       */
      //EnvironmentSettings env = EnvironmentSettings.inStreamingMode();
      // TableEnvironment tenv = TableEnvironment.create(env);

      /*
       * 创建table执行环境 方式2
       */
      StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
      env.setRuntimeMode(RuntimeExecutionMode.STREAMING);
      StreamTableEnvironment tenv = StreamTableEnvironment.create(env);
      //env.setParallelism(1);
      //env.enableCheckpointing(1000);
      //env.getCheckpointConfig().setCheckpointStorage("file:///D:/app/server/IDEA/idea-project/my-project/flink16/checkpoint");

      /*
      创建源表
       */
      tenv.executeSql("CREATE TABLE KafkaTable (\n" +
              "  `id` INT,\n" +
              "  `name` STRING,\n" +
              "  `age` INT,\n" +
              "  `gender` STRING,\n" +
              "  `partition` BIGINT METADATA VIRTUAL,\n" +
              "  `offset` BIGINT METADATA VIRTUAL\n" +
              //表达式字段
//              "  `cost` AS age * 3\n" +
              //元数据字段
//              "  `rt` TIMESTAMP_LTZ(3) METADATA FROM 'timestamp'" +
              ") WITH (\n" +
              "  'connector' = 'kafka',\n" +
              "  'topic' = 'first',\n" +
              "  'properties.bootstrap.servers' = 'hadoop102:9092',\n" +
              "  'properties.group.id' = 'g1',\n" +
              "  'scan.startup.mode' = 'earliest-offset',\n" +
              "  'format' = 'json',\n" +
              "  'json.fail-on-missing-field' = 'false',\n" +
              "  'json.ignore-parse-errors' = 'true'\n" +
              ")");


      // 列出 default_catalog中的库和表
      tenv.executeSql("show catalogs").print();
      tenv.executeSql("use catalog default_catalog");
      tenv.executeSql("show databases").print();
      tenv.executeSql("use default_database");
      tenv.executeSql("show tables").print();

      //sql表转table对象
      Table kafkaTable = tenv.from("KafkaTable");

      //执行查询语句
      tenv.executeSql("select * from KafkaTable").print();
      //tenv.executeSql("select gender, count(1) from KafkaTable group by gender ").print();



   }

}
