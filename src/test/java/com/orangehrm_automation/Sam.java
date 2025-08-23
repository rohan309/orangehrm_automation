package com.orangehrm_automation;

public class Sam {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("No command-line arguments provided.");
        } else {
            System.out.println("Command-line arguments provided:");
            for (String arg : args) {
                System.out.println(arg);
            }
        }
    }
}
