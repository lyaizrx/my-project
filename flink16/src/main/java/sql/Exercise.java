package sql;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * ClassName: ExerciseDemo
 * Package: sql
 * Description:
 *    基本： kafka中有如下数据：
 *       {"id":1,"name":"zs","nick":"tiedan","age":18,"gender":"male"}
 *    高级：kafka中有如下数据：
 *      {"id":1,"name":{"formal":"zs","nick":"tiedan"},"age":18,"gender":"male"}
 *      现在需要用flinkSql来对上述数据进行查询统计：
 *        截止到当前,每个昵称,都有多少个用户
 *        截止到当前,每个性别,年龄最大值
 *
 * @Author ly
 * @Create 2022/12/20 14:30
 * @Version 1.0
 */
public class Exercise {

   public static void main(String[] args) {
      //创建执行环境
      StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
      StreamTableEnvironment tenv = StreamTableEnvironment.create(env);

      //创建源表
      tenv.executeSql("CREATE TABLE KafkaTable (\n" +
              "  `id` BIGINT,\n" +
              "  `name` STRING,\n" +
              "  `nick` STRING,\n" +
              "  `age` BIGINT,\n" +
              "  `gender` STRING\n" +
              ") WITH (\n" +
              "  'connector' = 'kafka',\n" +
              "  'topic' = 'first',\n" +
              "  'properties.bootstrap.servers' = 'hadoop102:9092',\n" +
              "  'properties.group.id' = 'tg',\n" +
              "  'scan.startup.mode' = 'latest-offset',\n" +
              "  'format' = 'json',\n" +
              "  'json.fail-on-missing-field' = 'false',\n" +
              "  'json.ignore-parse-errors' = 'true'\n" +
              ")");

      //创建目标表
      tenv.executeSql("CREATE TABLE pageviews_per_region (\n" +
              "  nick STRING,\n" +
              "  total_user BIGINT,\n" +
              "  PRIMARY KEY (nick) NOT ENFORCED\n" +
              ") WITH (\n" +
              "  'connector' = 'upsert-kafka',\n" +
              "  'topic' = 'second',\n" +
              "  'properties.bootstrap.servers' = 'hadoop102:9092',\n" +
              "  'value.fields-include' = 'ALL',\n" +
              "  'key.format' = 'json',\n" +
              "  'value.format' = 'json'\n" +
              ")");

      tenv.executeSql("insert into pageviews_per_region select nick, count(*) as total_user from KafkaTable group by nick");

      //创建目标表
      tenv.executeSql("CREATE TABLE upsert_kafka (\n" +
              "  nick STRING,\n" +
              "  total_user BIGINT,\n" +
              "  PRIMARY KEY (nick) NOT ENFORCED\n" +
              ") WITH (\n" +
              "  'connector' = 'upsert-kafka',\n" +
              "  'topic' = 'second',\n" +
              "  'properties.bootstrap.servers' = 'hadoop102:9092',\n" +
              "  'properties.group.id' = 'tg1',\n" +
              "  'value.fields-include' = 'ALL',\n" +
              "  'key.format' = 'json',\n" +
              "  'value.format' = 'json'\n" +
              ")");

      tenv.executeSql("select nick, total_user from upsert_kafka").print();


   }

}
