/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexampleproject.threads.demo17;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class Processor implements Runnable {

    private List<Integer> list;
    private List<Integer> hatali;

    public Processor(List<Integer> list, List<Integer> hatali) {
        this.list = list;
        this.hatali = hatali;
    }

    public void run() {
        for (int i = 0; i < list.size(); i++) {
            this.hatali.add(i);

        }
    }
}
