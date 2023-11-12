package com.ly.day1;

/**
 * ClassName: MyThreadTest1
 * Package: com.ly.day1
 * Description:
 *
 * @Author ly
 * @Create 2023/10/27 11:04
 * @Version 1.0
 */
public class MyThreadTest1 {
    public static void main(String[] args) {

        final Thread thread = new Thread(new MyRunnable());
        thread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
        }
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
        }
    }
}
