package day;

/**
 * ClassName: ExceptionTest
 * Package: day
 * Description:
 *
 * @Author ly
 * @Create 2023/10/26 16:56
 * @Version 1.0
 */
public class ExceptionTest {
   public static void main(String[] args) {
      try {
         // 步骤4：在代码中使用throw语句引发自定义异常
         throw new CustomException("This is a custom exception.");
      } catch (CustomException e) {
         System.out.println("Caught custom exception: " + e.getMessage());
         e.customMethod(); // 调用自定义方法
      }
   }
}
