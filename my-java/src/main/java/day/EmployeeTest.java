package day;

import bean.Employee;

/**
 * ClassName: EmployeeTest
 * Package: day
 * Description:
 *
 * @Author ly
 * @Create 2023/10/21 11:30
 * @Version 1.0
 */
public class EmployeeTest {
   public static void main(String[] args) {
      final Employee employee1 = new Employee();
      final Employee employee2 = new Employee();

      employee1.setId(1);
      employee1.setName("zhangsan");
      employee1.setAge(19);
      employee1.setSalary(1000.0);

      employee2.setId(2);
      employee2.setName("lisi");
      employee2.setAge(39);
      employee2.setSalary(2111.0);

      System.out.println("员工1的姓名：" + employee1.getName() + " 年龄：" + employee1.getAge() + " 薪水: " + employee1.getSalary()  );
      System.out.println("员工2的姓名：" + employee2.getName() + " 年龄：" + employee2.getAge() + " 薪水: " + employee2.getSalary()  );

   }
}
