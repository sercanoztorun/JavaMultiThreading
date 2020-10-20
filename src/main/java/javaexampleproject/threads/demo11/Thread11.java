/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexampleproject.threads.demo11;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Lenovo
 */
public class Thread11 {

    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void producer() throws InterruptedException {
        int value = 0;
        while (true) {

            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consumer() throws InterruptedException {
        Random random = new Random();
        System.out.println("CONSUMERRERRRR");
        while (true) {

            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }
                System.out.println("List size is: " + list.size());
                int value = list.removeFirst();
                System.out.print(";value is : " + value);

                lock.notify();
            }

            Thread.sleep(random.nextInt(1000));

        }
    }
}
