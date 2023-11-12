package day;

import lombok.Data;

/**
 * ClassName: AnimalTest
 * Package: day
 * Description:
 *
 * @Author ly
 * @Create 2023/10/23 9:39
 * @Version 1.0
 */
public class AnimalTest {
   public static void main(String[] args) {
      final Animal animal = new Animal();
      animal.setName("金蟾");
      animal.setLegs(4);
      System.out.println(animal.getName());
      System.out.println(animal.getLegs());
      animal.eat();

   }

   public class Dog extends Object {
      public void eat() {
         System.out.println("Dog eat!!!");
      }
   }
}

@Data
class Animal {
   private String name;
   private int legs;

   public void eat(){
      System.out.println("Animal can eat");
   }


}
