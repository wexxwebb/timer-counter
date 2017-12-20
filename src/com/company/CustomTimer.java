package com.company;

public class CustomTimer implements Runnable {

    private Object monitor;
    private int limit;
    private String message;

    public CustomTimer(Object monitor, int limit, String message) {
        this.monitor = monitor;
        this.limit = limit;
        this.message = message;
    }

    @Override
    public void run() {
        int count = 0;
        synchronized (monitor) {
            while (true) {
                try {
                    monitor.wait();
                    count++;
                    if (count == limit) {
                        count = 0;
                        System.out.println(message);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
