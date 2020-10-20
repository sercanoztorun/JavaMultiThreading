/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexampleproject.threads.demo15;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class Thread15 {

    public static void main(String args[]) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
//        executor.submit(new Runnable() {
//            @Override
//            public void run() {
//                Random random = new Random();
//                int duration = random.nextInt(4000);
//                System.out.println("Starting");
//                try {
//                    Thread.sleep(duration);
//                } catch (InterruptedException ex) {
//
//                }
//                System.out.println("Finished");
//            }
//        });
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                Random random = new Random();
                int duration = random.nextInt(4000);

                if (duration > 100) {
                    throw new IOException("Sleeping for too long");
                }
                System.out.println("Starting");
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException ex) {

                }
                System.out.println("Finished");
                return duration;

            }
        });
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        try {
            System.out.println("Result is :" + future.get());
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread15.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Thread15.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
