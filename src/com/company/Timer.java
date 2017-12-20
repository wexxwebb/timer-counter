package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer implements Runnable {

    private SimpleDateFormat sdf = new SimpleDateFormat("hh : mm : ss");
    private Object monitor;

    public Timer(Object monitor) {
        this.monitor = monitor;
    }

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
}
