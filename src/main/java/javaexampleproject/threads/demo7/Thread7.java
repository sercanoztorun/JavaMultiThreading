/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexampleproject.threads.demo7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
class Processor implements Runnable {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Starting:" + id);
        try {

            Thread.sleep(2000);

        } catch (InterruptedException ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Completed:" + id);

    }
}

public class Thread7 {

    public static void main(String args[]) {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(10);
            for (int i = 0; i < 10; i++) {
                executor.submit(new Processor(i));
            }
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.DAYS);//timeout time.
            System.out.println("All tasks submitted.");

            
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread7.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
