package core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * ClassName: FlinkTest
 * Package: test
 * Description:
 *
 * @Author ly
 * @Create 2022/12/17 23:06
 * @Version 1.0
 */
public class FlinkTest {

   public static void main(String[] args) {
      StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
      env.setParallelism(1);

      DataStreamSource<String> source = env.socketTextStream("hadoop102", 9999);
      SingleOutputStreamOperator<EventBean> mapDs = source.map((MapFunction<String, EventBean>) s -> {
         String[] split = s.split(",");
         return new EventBean(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]), split[3]);
      });

      mapDs.print();

      try {
         env.execute();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @Data
   @NoArgsConstructor
   @AllArgsConstructor
   public static class EventBean{
      private int id;
      private String name;
      private int age;
      private String gender;
   }

}
