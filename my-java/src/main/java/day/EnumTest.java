package day;

/**
 * ClassName: EnumTest
 * Package: day
 * Description:
 *
 * @Author ly
 * @Create 2023/10/26 9:12
 * @Version 1.0
 */
public class EnumTest {
    public static void main(String[] args) {
        final SeasonTest summer = SeasonTest.SUMMER;
        System.out.println(summer.name());

        final SeasonTest s = SeasonTest.SPRING;

    }
}
