package com.ly.day2;

import bean.Person1;

/**
 * ClassName: FanShe
 * Package: com.ly.day2
 * Description:
 *
 * @Author ly
 * @Create 2023/11/2 16:52
 * @Version 1.0
 */
public class FanShe {
   public static void main(String[] args) throws InstantiationException, IllegalAccessException {
      /*Object o = new String("aa");
      System.out.println(o.getClass().getName());*/

      //1
      Class<Person1> clazz = Person1.class;
      Person1 person1 = clazz.newInstance();

      //2
      Person1 p1 = new Person1();
      Class<? extends Person1> aClass = p1.getClass();

      //3
      String className = "bean.Person1";
      try {
         Class<?> aClass1 = Class.forName(className);
         System.out.println(aClass1);
      } catch (ClassNotFoundException e) {
         throw new RuntimeException(e);
      }

      System.out.println(clazz == aClass);


   }
}
