package sql;

import org.apache.flink.table.api.*;

/**
 * ClassName: ColumnDetail2TableApiDemo7
 * Package: sql
 * Description:
 *
 * @Author ly
 * @Create 2022/12/20 16:09
 * @Version 1.0
 */
public class ColumnDetail2TableApi {

   public static void main(String[] args) {

      TableEnvironment tenv = TableEnvironment.create(EnvironmentSettings.inStreamingMode());

      // 建表（数据源表）
      // {"id":4,"name":"zs","nick":"tiedan","age":18,"gender":"male"}
      tenv.createTable("t_person",
              TableDescriptor
                      .forConnector("kafka")
                      .schema(Schema.newBuilder()
                              // column是声明物理字段到表结构中来
                              .column("id", DataTypes.INT())
                              // column是声明物理字段到表结构中来
                              .column("name", DataTypes.STRING())
                              // column是声明物理字段到表结构中来
                              .column("nick", DataTypes.STRING())
                              // column是声明物理字段到表结构中来
                              .column("age", DataTypes.INT())
                              // column是声明物理字段到表结构中来
                              .column("gender", DataTypes.STRING())
                              // 声明表达式字段
                              .columnByExpression("guid","id")
                              // 声明表达式字段
                              /*.columnByExpression("big_age",$("age").plus(10))*/
                              // 声明表达式字段
                              .columnByExpression("big_age","age + 10")
                              // isVirtual 是表示： 当这个表被sink表时，该字段是否出现在schema中
                              // 声明元数据字段
                              .columnByMetadata("offs",DataTypes.BIGINT(),"offset",true)
                              // 声明元数据字段
                              .columnByMetadata("ts",DataTypes.TIMESTAMP_LTZ(3),"timestamp",true)
                              /*.primaryKey("id","name")*/
                              .build())
                      .format("json")
                      .option("topic","first")
                      .option("properties.bootstrap.servers","hadoop102:9092")
                      .option("properties.group.id","g1")
                      .option("scan.startup.mode","latest-offset")
                      .option("json.fail-on-missing-field","false")
                      .option("json.ignore-parse-errors","true")
                      .build()
      );

      tenv.executeSql("select * from t_person").print();


   }

}
