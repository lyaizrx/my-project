package com.ly.day1;

/**
 * ClassName: MyThread
 * Package: com.ly.day1
 * Description:
 *
 * @Author ly
 * @Create 2023/10/27 10:36
 * @Version 1.0
 */
public class MyThreadTest {
    public static void main(String[] args) {
        final MyThread t1 = new MyThread();
        t1.start();

        final MyThread t2 = new MyThread();
        t2.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {

                    System.out.println("11111");
                }
            }
        }.start();

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
        }
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
        }
    }
}
