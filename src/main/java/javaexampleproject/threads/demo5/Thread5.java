/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexampleproject.threads.demo5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
//syncronized'ın önemi eğer kullanılmasaydı 20000 değerini her zaman veremeyecekti.multi threading yapıldığında count farklı yönlerden arttırıldığı için count++ komutu her zaman düzgün çalışmaz.
public class Thread5 {
    List newList = Collections.synchronizedList(new ArrayList());
    private int count = 0;
    String s = "";

    public synchronized void increment() {
        count++;
    }

    public static void main(String args[]) {
        Thread5 app = new Thread5();
        app.doWork();
    }

    public void doWork() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10000; i++) {
                    newList.add(i);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10000; i++) {
                    newList.add(i);
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread5.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Count is " + newList.size());
    }

}
