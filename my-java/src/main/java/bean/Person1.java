package bean;

/**
 * ClassName: Persion1
 * Package: bean
 * Description:
 *
 * @Author ly
 * @Create 2023/11/2 16:58
 * @Version 1.0
 */
public class Person1 {
   public String name;
   private int age;

   public Person1() {
   }

   public Person1(String name, int age) {
      this.name = name;
      this.age = age;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }
}
