package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Employee
 * Package: bean
 * Description:
 *
 * @Author ly
 * @Create 2023/10/21 11:27
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
   private int id;
   private String name;
   private int age;
   private double salary;

   /**
    * 测试代码111
    */
   public static void show(){
      System.out.println("name: ");
   }

   public void show(int age){
      System.out.println(age);
   }
}
