package com.example.javamavenjunithelloworld;

/**
 * A very basic program that demonstrates the use of JUnit tests.
 */
public class HelloApp {
    public static final int EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD = 2;
    public static final int EXIT_STATUS_HELLO_FAILED = 4;
    static int DEFAULT_TIMES = 3;

    public static void main(String[] args) {
        int times = DEFAULT_TIMES;
        
        // Handle command line arguments
        if (args.length >= 1) {
            try {
                times = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("I don't understand the parameter. Please provide a number.");
                System.err.println("Parameter was: [" + args[0] + "]");
                System.exit(EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD);
            }
        }

        Hello hi = new Hello();
        try {
            hi.setTimes(times);
            hi.sayHello(System.out);
        } catch (IllegalArgumentException e) {
            System.err.println("Something went wrong: " + e.getMessage());
            System.exit(EXIT_STATUS_HELLO_FAILED);
        }
    }
}
