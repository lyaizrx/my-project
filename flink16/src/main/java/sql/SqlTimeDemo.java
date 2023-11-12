package sql;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * ClassName: SqlTimeDemo
 * Package: sql
 * Description:
 *
 * @Author ly
 * @Create 2022/12/19 23:15
 * @Version 1.0
 */
public class SqlTimeDemo {

   public static void main(String[] args) {
      StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
      StreamTableEnvironment tenv = StreamTableEnvironment.create(env);

      tenv.executeSql("select TO_TIMESTAMP_LTZ(1671463098, 0)").print();
   }

}
