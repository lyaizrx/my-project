package com.ly.day2;

import java.io.UnsupportedEncodingException;

/**
 * ClassName: StringDemo
 * Package: com.ly.day2
 * Description:
 *
 * @Author ly
 * @Create 2023/10/28 14:27
 * @Version 1.0
 */
public class StringDemo {
   public static void main(String[] args) {
      String s1 = "hello中国";

      /*char[] c = s1.toCharArray();
      for (char c1 : c) {
         System.out.println(c1);
      }*/

      byte[] b = new byte[0];
      try {
         b = s1.getBytes("UTF-8");
      } catch (UnsupportedEncodingException e) {
         throw new RuntimeException(e);
      }
      for (byte b1 : b) {
         System.out.println(b1);
      }

      System.out.println(new String(b));

   }
}
