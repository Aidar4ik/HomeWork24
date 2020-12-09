package com.company.VariantA;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;


public class Main1 {

    public static final int PEOPLE = 10;

    public static void main(String[] args) {

        //Uploader
        CountDownLatch cdl = new CountDownLatch(1);
        Semaphore semaphore = new Semaphore(1);
        new Uploader("File ", semaphore, cdl).start();
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Downloader
        CountDownLatch cdl1 = new CountDownLatch(PEOPLE);
        Semaphore semaphore1 = new Semaphore(3);
        for (int i = 1; i <= PEOPLE; i++) {
            new Downloaders(i + " Человек ", semaphore1, cdl).start();
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(cdl.getCount()<=0){
            System.out.println("Файл будет удален с сервера после того как "+PEOPLE+" закончит загрузку");
        }
    }
}
