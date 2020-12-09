package com.company.VariantB;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main2 {

    public synchronized static void main(String[] args) {
        // write your code here
        CountDownLatch cdl=new CountDownLatch(100);
        Semaphore semaphore=new Semaphore(4);
        for (int i = 1; i <=100 ; i++) {
                new PassengerThread(i+" Пассажир ",cdl,semaphore).start();
        }
        try {
            cdl.await();
            System.out.println("Автобус набрал всех пассажиров и поехал в Ош");
        } catch (Exception e) {
        }
    }
}
