package day;

import java.util.ArrayList;

/**
 * ClassName: Testday8
 * Package: day
 * Description:
 *
 * @Author ly
 * @Create 2023/10/26 10:25
 * @Version 1.0
 */
public class Testday8 {
    public static void main(String[] args) {
        try {
            ArrayList<Integer> a = new ArrayList<>();
            a.add(Integer.parseInt("1"));
            System.out.println(1 / 0);
            System.out.println(a.get(0));

        } catch (ArithmeticException e) {
            e.printStackTrace();
//            System.out.println(e.getMessage());
            //System.out.println("haha");
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }finally {

        }

        System.out.println("1111111");

    }
}
