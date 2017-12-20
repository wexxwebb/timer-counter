package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    private static SimpleDateFormat sdf = new SimpleDateFormat("hh : mm : ss");

    private static final Object monitor = new Object();

    public static void main(String[] args) {
        Thread one_second = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(sdf.format(new Date()));
                    synchronized (monitor) {
                        monitor.notifyAll();
                    }
                }
            }
        });
        one_second.start();

        Thread five_seconds = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                synchronized (monitor) {
                    while (true) {
                        try {
                            monitor.wait();
                            count++;
                            if (count == 5) {
                                count = 0;
                                System.out.println("Five second");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        five_seconds.start();

        Thread seven_seconds = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                synchronized (monitor) {
                    while (true) {
                        try {
                            monitor.wait();
                            count++;
                            if (count == 7) {
                                count = 0;
                                System.out.println("Seven second");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        seven_seconds.start();
    }
}
