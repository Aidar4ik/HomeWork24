package com.company.VariantA;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Uploader extends Thread {

    private Semaphore semaphore;
    private CountDownLatch cdl;
    private int mb = 500, smb = 20;

    public Uploader(String name, Semaphore semaphore, CountDownLatch cdl) {
        super(name);
        this.cdl = cdl;
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            System.out.println("Загрузка " + this.getName() + " на сервервер началась  |");
            semaphore.acquire();
            for (int i = 1; i <= mb; i++) {
                System.out.printf("█");
                mb -= smb;
                sleep(1000);
            }
            System.out.println("\n" + this.getName() + " загружен на сервер");

        } catch (Exception e) { }finally {
            semaphore.release();
            cdl.countDown();
        }
    }
}
