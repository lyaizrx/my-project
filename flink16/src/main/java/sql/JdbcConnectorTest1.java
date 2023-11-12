package sql;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * ClassName: JdbcConnectorTest1
 * Package: sql
 * Description:
 *
 * @Author ly
 * @Create 2022/12/20 19:34
 * @Version 1.0
 */
public class JdbcConnectorTest1 {
   public static void main(String[] args) throws Exception {

      StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
      env.setRuntimeMode(RuntimeExecutionMode.STREAMING);

      EnvironmentSettings environmentSettings = EnvironmentSettings.inStreamingMode();
      StreamTableEnvironment tenv = StreamTableEnvironment.create(env,environmentSettings);


      // 建表来映射 mysql中的 flinktest.stu
      tenv.executeSql(
              "create table flink_stu(\n" +
                      "   id  int primary key,\n" +
                      "   name string,\n" +
                      "   age int,\n" +
                      "   gender string\n" +
                      ") with (\n" +
                      "  'connector' = 'jdbc',\n" +
                      "  'url' = 'jdbc:mysql://hadoop102:3306/flinktest',\n" +
                      "  'table-name' = 'stu',\n" +
                      "  'username' = 'root',\n" +
                      "  'password' = 'root' \n" +
                      ")"
      );

      DataStreamSource<String> source = env.socketTextStream("hadoop102", 9999);

      tenv.executeSql("select * from flink_stu").print();

      source.print();


      env.execute();
   }
}
