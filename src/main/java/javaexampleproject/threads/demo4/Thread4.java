/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexampleproject.threads.demo4;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
class Processor extends Thread {

    private volatile boolean running = true;

    public void run() {

        while (running) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {

            }
        }
    }

    public void shutdown() {
        running = false;
    }
}

public class Thread4 {

    public static void main(String args[]) {
        Processor proc1 = new Processor();
        proc1.start();

        System.out.println("Stop return");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        

        proc1.shutdown();
    }

}
