package com.company;

import java.math.BigInteger;
import java.util.Scanner;

public class Question1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number to calculate its factorial: ");
        int number = scanner.nextInt();

        BigInteger factorial = calculateFactorial(number);

        System.out.println("The factorial of " + number + " is: " + factorial);
    }

    // Method to calculate the factorial of a number
    public static BigInteger calculateFactorial(int number) {

        BigInteger result = BigInteger.ONE;

        // Multiply result by each number from 2 up to the input number
        for (int i = 2; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }
}
