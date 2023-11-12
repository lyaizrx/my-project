package sql;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;

/**
 * ClassName: ColumnDetail1lDemo7
 * Package: sql
 * Description: 三种字段
 *
 * @Author ly
 * @Create 2022/12/20 16:01
 * @Version 1.0
 */
public class ColumnDetail1 {

   public static void main(String[] args) {
      TableEnvironment tenv = TableEnvironment.create(EnvironmentSettings.inStreamingMode());

      // 建表（数据源表）
      // {"id":4,"name":"zs","nick":"tiedan","age":18,"gender":"male"}
      tenv.executeSql(
              "create table t_person                                 "
                      + " (                                                   "
                      + "   id int ,                                          "  // -- 物理字段
                      + "   name string,                                      "  // -- 物理字段
                      + "   nick string,                                      "
                      + "   age int,                                          "
                      + "   gender string ,                                   "
                      + "   guid as id,                                       "  // -- 表达式字段（逻辑字段）
                      + "   big_age as age + 10 ,                             "  // -- 表达式字段（逻辑字段）
                      + "   offs  bigint metadata from 'offset' ,             "   // -- 元数据字段
                      + "   ts TIMESTAMP_LTZ(3) metadata from 'timestamp'    "   // -- 元数据字段
                      /*+ "   PRIMARY KEY(id,name) NOT ENFORCED                 "*/    // -- 主键约束
                      + " )                                                   "
                      + " WITH (                                              "
                      + "  'connector' = 'kafka',                             "
                      + "  'topic' = 'first',                              "
                      + "  'properties.bootstrap.servers' = 'hadoop102:9092',   "
                      + "  'properties.group.id' = 'g1',                      "
                      + "  'scan.startup.mode' = 'latest-offset',           "
                      + "  'format' = 'json',                                 "
                      + "  'json.fail-on-missing-field' = 'false',            "
                      + "  'json.ignore-parse-errors' = 'true'                "
                      + " )                                                 "
      );

      tenv.executeSql("desc t_person").print();
      tenv.executeSql("select * from t_person where id>2").print();

   }

}
