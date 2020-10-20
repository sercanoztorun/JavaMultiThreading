/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexampleproject.threads.demo8;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
class Processor implements Runnable {

    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        System.out.println("Started.");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        latch.countDown();
    }
}

public class Thread8 {

    public static void main(String args[]) {
        CountDownLatch latch = new CountDownLatch(10);

        ExecutorService executor = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 10; i++) {
            executor.submit(new Processor(latch));
        }
        try {
            latch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread8.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Completed");
    }

}
