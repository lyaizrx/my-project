package bean;

/**
 * ClassName: InterfaceTest
 * Package: bean
 * Description:
 *
 * @Author ly
 * @Create 2023/10/25 14:17
 * @Version 1.0
 */
public class InterfaceTest implements Flyable{
    @Override
    public void fly() {

    }
}

interface Flyable{

    void fly();

}
