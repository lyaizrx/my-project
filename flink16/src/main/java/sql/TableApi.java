package sql;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.Schema;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableDescriptor;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import static org.apache.flink.table.api.Expressions.$;

/**
 * ClassName: Demo2_TableApi
 * Package: sql
 * Description: 使用table api建表
 *
 * @Author ly
 * @Create 2022/12/20 10:44
 * @Version 1.0
 */
public class TableApi {

   public static void main(String[] args) {
      // 混合环境创建
      StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
      env.setRuntimeMode(RuntimeExecutionMode.STREAMING);
      StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

      //建表
      Table kafkaTable = tableEnv.from(TableDescriptor.forConnector("kafka")
              .schema(Schema.newBuilder()
                      .column("id", DataTypes.INT())
                      .column("name", DataTypes.STRING())
                      .column("age", DataTypes.INT())
                      .column("gender", DataTypes.STRING())
                      .build())
              .format("json")
              // 连接器及format格式的相关参数
              .option("topic", "test")
              .option("properties.bootstrap.servers", "hadoop102:9092")
              .option("properties.group.id", "g")
              .option("scan.startup.mode", "earliest-offset")
              .option("json.fail-on-missing-field", "false")
              .option("json.ignore-parse-errors", "true")
              .build());

      // 查询
      Table table2 = kafkaTable.groupBy($("gender"))
              .select($("gender"),$("age").avg().as("avg_age"));

      // 输出
      table2.execute().print();

      /*
       * 将一个已创建好的 table对象，注册成sql中的视图名
       */
      tableEnv.createTemporaryView("kafka_table",kafkaTable);
      // 然后就可以写sql语句来进行查询了
      tableEnv.executeSql("select  gender,avg(age) as avg_age from kafka_table group by gender").print();


   }

}
