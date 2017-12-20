package com.company;

public class Main {

    private static final Object monitor = new Object();

    public static void main(String[] args) {
        Thread one_second = new Thread(new Timer(monitor));
        one_second.start();

        Thread five_seconds = new Thread(new CustomTimer(monitor, 5, "Five second!"));
        five_seconds.start();

        Thread seven_seconds = new Thread(new CustomTimer(monitor, 7, "Seven seconds!"));
        seven_seconds.start();
    }
}
