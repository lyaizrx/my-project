package com.ly.day1;

/**
 * ClassName: WindowTest
 * Package: com.ly.day1
 * Description:
 *
 * @Author ly
 * @Create 2023/10/27 16:28
 * @Version 1.0
 */
public class WindowTest {
    public static void main(String[] args) {
        Window w1 = new Window();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w1);
        Thread t3 = new Thread(w1);

        t1.start();
        t2.start();
        t3.start();

    }
}

class Window implements Runnable {
    int ticket = 100;
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (this) {
                if(ticket > 0){
                    System.out.println(Thread.currentThread().getName() + "窗口卖票： " + ticket);
                    ticket--;
                } else {
                    break;
                }
            }

        }

    }
}
