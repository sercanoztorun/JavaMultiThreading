/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexampleproject.threads.demo17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.apache.commons.collections4.ListUtils;

/**
 *
 * @author Lenovo
 */
public class Thread17 {

    public static void main(String args[]) {
        try {
            List newList = Collections.synchronizedList(new ArrayList());
            List<Integer> list = new ArrayList<Integer>();

            for (int i = 0; i < 50009; i++) {
                list.add(i);
            }
            int parseSize = getParseSize(list.size());
            List<List<Integer>> output = ListUtils.partition(list, parseSize);

//            for (int i = 0; i < output.size(); i++) {
//                System.out.println(output.get(i));
//            }
//            System.out.println(output.size());

            int threadSize = 10;
            if (parseSize == list.size()) {
                threadSize = 1;
            }

            ExecutorService executor = Executors.newFixedThreadPool(threadSize);
            for (int i = 0; i < threadSize; i++) {
                executor.submit(new Processor(output.get(i),newList));
            }
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.DAYS);//timeout time.
            System.out.println("hatali size : " + newList.size());

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static int getParseSize(int size) {
        if (size < 100) {
            return size;
        } else {
            if (size % 10 == 0) {
                return size / 10;
            } else {
                return (size / 10) + 1;
            }
        }
    }

}
