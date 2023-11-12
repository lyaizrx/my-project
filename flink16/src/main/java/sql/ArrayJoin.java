package sql;

import org.apache.flink.table.annotation.DataTypeHint;
import org.apache.flink.table.annotation.FunctionHint;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.functions.TableFunction;
import org.apache.flink.types.Row;

import static org.apache.flink.table.api.Expressions.array;
import static org.apache.flink.table.api.Expressions.row;

/**
 * ClassName: ArrayJoin
 * Package: sql
 * Description:
 *
 * @Author ly
 * @Create 2022/12/20 19:54
 * @Version 1.0
 */
public class ArrayJoin {
   public static void main(String[] args) {
      TableEnvironment tenv = TableEnvironment.create(EnvironmentSettings.inStreamingMode());

      Table table = tenv.fromValues(DataTypes.ROW(
                      DataTypes.FIELD("id", DataTypes.INT()),
                      DataTypes.FIELD("name", DataTypes.STRING()),
                      DataTypes.FIELD("tags", DataTypes.ARRAY(DataTypes.STRING())))
              , row("1", "zs", array("stu", "child"))
              , row("2", "bb", array("miss"))
      );

      tenv.createTemporaryView("t",table);


      tenv.executeSql("select t.id,t.name,x.tag from t cross join unnest(tags) as x(tag)")/*.print()*/;


      tenv.createTemporarySystemFunction("mysplit",MySplit.class);
      tenv.executeSql("select t.id,t.name,tag from t, lateral table(mysplit(tags)) ")/*.print()*/;
      tenv.executeSql("select t.id,t.name,x.tag2 from t, lateral table(mysplit(tags)) x(tag2)")/*.print()*/;
      tenv.executeSql("select t.id,t.name,tag from t left join lateral table(mysplit(tags)) on true")/*.print()*/;
      tenv.executeSql("select t.id,t.name,x.tag2 from t left join lateral table(mysplit(tags)) x(tag2) on true").print();
   }

   @FunctionHint(output = @DataTypeHint("ROW<tag string>"))
   public static class MySplit extends TableFunction<Row> {

      public void eval(String[] arr){
         for (String s : arr) {
            collect(Row.of(s));
         }
      }

   }
}
