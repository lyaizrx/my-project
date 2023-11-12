package day;

/**
 * ClassName: CustomException
 * Package: day
 * Description:
 *
 * @Author ly
 * @Create 2023/10/26 16:55
 * @Version 1.0
 */
// 步骤1：创建自定义异常类
public class CustomException extends Exception {
   // 步骤2：添加构造函数
   public CustomException(String message) {
      super(message);
   }

   // 步骤3：添加其他方法和属性
   public void customMethod() {
      System.out.println("Custom Exception Method");
   }
}



