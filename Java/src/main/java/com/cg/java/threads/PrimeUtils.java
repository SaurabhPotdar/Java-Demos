package com.cg.java.threads;

public class PrimeUtils {

	public static int calculateNthPrime(int n) {
		int numberOfPrimesFound = 0;
		int number = 1;
		int i = 0;
		while (numberOfPrimesFound < n) {
			number++;
			for (i = 2; i <= number && number % i != 0; i++) {
			}
			if (i == number) {
				numberOfPrimesFound++;
			}
		}
		return number;
	}
}
