package com.ly.day2;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ClassName: StringT
 * Package: com.ly.day2
 * Description:
 *
 * @Author ly
 * @Create 2023/10/28 19:25
 * @Version 1.0
 */
public class StringT {

   public static void main(String[] args) {
      StringBuffer sb = new StringBuffer();
      sb.append("a");
      StringBuilder sb1 = new StringBuilder();

      long l = System.currentTimeMillis();
      //System.out.println(l);

      Date date = new Date();
      //System.out.println(date.getTime());
      System.out.println(date);

      SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String format = sd.format(date);
      System.out.println(format);

      try {
         Date parse = sd.parse("2023-10-30 10:05:46");
         System.out.println(parse);
      } catch (ParseException e) {
         throw new RuntimeException(e);
      }

      /*Date date = new Date(1698630764163L);
      System.out.println(date);
      System.out.println(date.getTime());*/

   }

   @Test
   public void test(){
      Calendar instance = Calendar.getInstance();
      //System.out.println(instance.getClass());

   }

}
