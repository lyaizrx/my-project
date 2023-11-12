package com.ly.day1;

/**
 * ClassName: BankTest
 * Package: com.ly.day1
 * Description:
 *
 * @Author ly
 * @Create 2023/10/28 10:49
 * @Version 1.0
 */
public class BankTest {
   public static void main(String[] args) {
      Bank bank = Bank.getInstance();
   }
}

class Bank{
   private static volatile Bank bank = null;

   private Bank(){

   }

   public static Bank getInstance(){
      if(bank == null){
         synchronized(Bank.class){
            if(bank == null){
               bank = new Bank();
            }
         }
      }
      return bank;
   }

}
