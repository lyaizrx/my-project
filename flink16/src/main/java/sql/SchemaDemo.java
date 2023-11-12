package sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.Schema;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * ClassName: SchemaDemo
 * Package: sql
 * Description:
 *
 * @Author ly
 * @Create 2022/12/19 16:16
 * @Version 1.0
 */
public class SchemaDemo {

   public static void main(String[] args) {
      StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
      StreamTableEnvironment tenv = StreamTableEnvironment.create(env);
      DataStreamSource<String> source = env.socketTextStream("hadoop100", 9999);
      SingleOutputStreamOperator<User> userMap = source.map(new MapFunction<String, User>() {
         @Override
         public User map(String s) throws Exception {
            String[] split = s.split(",");
            return new User(Long.parseLong(split[0]), split[1]);
         }
      });

      Table table = tenv.fromDataStream(userMap, Schema.newBuilder()
              //物理字段
              .column("id", DataTypes.BIGINT())
              .column("name", DataTypes.STRING())
              //表达式字段
              .columnByExpression("age_expr", "age + 1")
              //元数据字段
              //.columnByMetadata()
              .build());

      tenv.createTemporaryView("demo", table);

      tenv.executeSql("SELECT * FROM demo").print();

      try {
         env.execute();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @Data
   @NoArgsConstructor
   @AllArgsConstructor
   public static class User {
      private Long id;
      private String name;
   }

}
