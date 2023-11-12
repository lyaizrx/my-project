package day;

import org.junit.Test;

import java.util.Scanner;

/**
 * ClassName: Testday3
 * Package: day
 * Description:
 *
 * @Author ly
 * @Create 2023/10/19 8:57
 * @Version 1.0
 */
public class Testday3 {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      System.out.println("next方式接收：");

      if (sc.hasNext()){
         String next = sc.next();
         System.out.println(next);
      }

      sc.close();
   }

   @Test
   public void test1(){
      int[] i1 = {1,2,3,4,5,6,7,8,9};
      for (int i : i1) {
         System.out.println(i);
      }
   }

   @Test
   public void test2(){
      int heartBeats = 110;
      if(heartBeats >=60 && heartBeats <= 100){
         System.out.println("正常");
      }else {
         System.out.println("有病");
      }
   }

   @Test
   public void test3(){
      int a = 13;
      if(a % 2 == 0){
         System.out.println("a是偶数");
      }else {
         System.out.println("a是奇数");
      }
   }

   @Test
   public void test4(){
      final int random = (int)(Math.random() * 100);
      System.out.println(random);

   }

   @Test
   public void test5(){
      int a = 1;
      switch (a){
         case 0:
            System.out.println("zero");
            break;
         case 1:
            System.out.println("one");
            break;
         default:
            System.out.println("other");
      }
   }

   @Test
   public void test6(){
      /*for (int i = 0; i < 10; i++) {
         System.out.println(i);
      }*/

      int a = 10;
      /*while (a > 0){
         System.out.println(a);
         a--;
      }*/

      /*do {
         System.out.println(a);
         a--;
      }while (a == 9);*/

      int m = 153;
      int bai = m / 100;
      int shi = m % 100 /10;
      int ge = m % 10;

      System.out.println(ge);

   }

   @Test
   public void test7(){
      int num = 0;
      int sum = 0;

      final long start = System.currentTimeMillis();
      System.out.println(start);
      for (int i = 0; i <= 100; i++) {
         if(i % 2 == 0){
            System.out.println(i);
            num++;
            sum += i;
         }
      }
      final long end = System.currentTimeMillis();
      System.out.println(end);

      System.out.println("偶数的个数: " + num);
      System.out.println("偶数的和: " + sum);
      System.out.println(end - start);
   }

   @Test
   public void test8(){
      
      for (int i = 100; i < 1000; i++) {
         int bai = i / 100;
         int shi = i % 100 /10;
         int ge = i % 10;
         if(i == bai*bai*bai + shi*shi*shi + ge*ge*ge){
            System.out.println(i + " 是水仙花数");
         }

      }

   }

}
