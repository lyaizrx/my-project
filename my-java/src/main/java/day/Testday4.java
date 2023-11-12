package day;

import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName: Testday4
 * Package: day
 * Description:
 *
 * @Author ly
 * @Create 2023/10/20 9:19
 * @Version 1.0
 */
public class Testday4 {
   public static void main(String[] args) {

   }

   @Test
   public void test1(){
      int[] i = {1,2,3,4,5};

      int sum = 0;
      for (int i1 : i) {
         sum += i1;
      }

      System.out.println(sum);
      System.out.println(i.length);
   }

   @Test
   public void test2() {
      int[] a = new int[3];
      System.out.println(a[0]);

      String[] b = new String[3];
      System.out.println(b[0]);

      char[] c = new char[3];
      System.out.println(c[0]);
   }

   @Test
   public void test3() {
      int[] i = {1,2,3,4,5};
      int[] i2 = {1,2,3,4,5};
      Arrays.sort(i);
      for (int i1 : i) {
         //System.out.println(i1);
      }

      System.out.println(Arrays.equals(i, i2));

      String s = "hello";
      String s1 = "hello";
      System.out.println(s.equals(s1));

      System.out.println(Arrays.toString(i));

      //Employee.show();
   }

}
