package com.company;

public class Question3 {

    public static void main(String[] args) {

        String inputString = "$180,240/y";

         // Convert the string to an integer 
        int result1 = convertToIntegerMethod1(inputString);
        int result2 = convertToIntegerMethod2(inputString);


        // result
        System.out.println("Output integer using Method 1: " + result1);
        System.out.println("Output integer using Method 2: " + result2);

    }

    public static int convertToIntegerMethod1(String input) {

        String newString = input.replaceAll("[^0-9]", "");

        // Convert the cleaned string to an integer
        return Integer.parseInt(newString);
    }

    public static int convertToIntegerMethod2(String input) {

        StringBuilder numericString = new StringBuilder();

        // Iterate through each character in the input string
        for (char c : input.toCharArray()) {

            // Check if the character is a digit
            if (Character.isDigit(c)) {
               
                numericString.append(c);
            }
        }

        
        return Integer.parseInt(numericString.toString());
    }



}
