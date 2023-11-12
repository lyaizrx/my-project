package com.ly.day2;

import java.util.*;

/**
 * ClassName: CollectionTest
 * Package: com.ly.day2
 * Description:
 *
 * @Author ly
 * @Create 2023/10/30 15:03
 * @Version 1.0
 */
public class CollectionTest {
   public static void main(String[] args){
      ArrayList al = new ArrayList();
      al.add("a");
      al.size();

      HashMap<Integer, String> map = new HashMap<>();
      map.put(1, "zs");
      map.put(2, "ls");
      map.put(3, "ww");

      Set<Map.Entry<Integer, String>> entries = map.entrySet();
      for (Map.Entry<Integer, String> entry : entries) {
         Integer key = entry.getKey();
         String value = entry.getValue();
         System.out.println("Key: " + key + ", Value: " + value);
      }

      Iterator<Integer> iterator = map.keySet().iterator();
      while (iterator.hasNext()) {
         Integer key = iterator.next();
         String value = map.get(key);
         System.out.println("Key: " + key + ", Value: " + value);
      }

      System.out.println();

      map.forEach((key, value) -> {
         System.out.println("Key: " + key + ", Value: " + value);
      });



   }
}
