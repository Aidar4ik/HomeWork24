package com.company.VariantA;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Downloaders extends Thread {
    private Semaphore semaphore;
    private CountDownLatch cdl;
    private int mb = 500, smb = 100;

    public Downloaders(String name, Semaphore semaphore, CountDownLatch cdl) {
        super(name);
        this.cdl = cdl;
        this.semaphore = semaphore;
    }

    public void run(){
        try {
            cdl.await();
                System.out.println(this.getName() + "ожидает загрузки");
                semaphore.acquire();
                System.out.println(this.getName() + "начал загрузку с сервера");
                for (int i = 0; i <= mb; i++) {
                    mb -= smb;
                    sleep(1000);
                }
                System.out.println("\n"+this.getName() + "закончил загрузку");

        }catch(InterruptedException e){}finally {
            cdl.countDown();
            semaphore.release();
        }
    }
}
