package sql;

import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.functions.ScalarFunction;
import org.apache.flink.types.Row;

/**
 * ClassName: CustomScalarFunction
 * Package: sql
 * Description: 用户自定义函数
 *
 * @Author ly
 * @Create 2022/12/20 19:58
 * @Version 1.0
 */
public class CustomScalarFunction {
   public static void main(String[] args) {

      TableEnvironment tenv = TableEnvironment.create(EnvironmentSettings.inStreamingMode());

      Table table = tenv.fromValues(
              DataTypes.ROW(
                      DataTypes.FIELD("name", DataTypes.STRING())),
              Row.of("aaa"),
              Row.of("bbb"),
              Row.of("ccc")
      );

      tenv.createTemporaryView("t",table);

      // 注册自定义的函数
      tenv.createTemporarySystemFunction("myupper",MyUpper.class);

      // 注册后，就能在sql中使用了
      tenv.executeSql("select myupper(name) from t").print();

   }


   public static class MyUpper extends ScalarFunction {

      public String eval(String str){
         return str.toUpperCase();
      }
   }
}
