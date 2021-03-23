package com.cg.java.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculatePrime {

    private static void printThreads(List<Thread> threads) {
        System.out.print("Threads Status: ");
        threads.forEach(thread -> System.out.print(thread.getState() + " "));
        System.out.println();
    }

    public static void main(String[] args) {

        List<Thread> threads = new ArrayList<>();


        Thread reporterThread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(10000);
                    printThreads(threads);
                }
            } catch(InterruptedException ex) {
                System.out.println("Status report Interrupted...");
            }
        });
        reporterThread.start();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter number: ");
                int N = scanner.nextInt();
                if (N == 0) {
                    reporterThread.interrupt();
                    try {
                        System.out.println("Waiting for all threads to finish");
                        waitForThreads(threads);
                        System.out.println("Done! " + threads.size() + " primes calculated");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                Thread t = new Thread(() -> {
                    int res = PrimeUtils.calculateNthPrime(N);
                    System.out.printf("\nRESULTS: %dth prime number is: %d\n", N, res);

                });
                threads.add(t);
                t.start();
            }
        }
    }

    private static void waitForThreads(List<Thread> threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }
}