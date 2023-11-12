package day;

/**
 * ClassName: Testday7
 * Package: day
 * Description:
 *
 * @Author ly
 * @Create 2023/10/25 9:02
 * @Version 1.0
 */
public class Testday7 {
   Bank b = Bank.getBank();

}

class Bank{
   private Bank() {
   }

   static {

   }


   private static Bank instance;
   public static Bank getBank(){
      if(instance==null){
         instance = new Bank();
      }
      return instance;
   }

}
