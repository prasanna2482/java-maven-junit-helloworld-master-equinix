package com.example.javamavenjunithelloworld;

/**
 * A very basic program that demonstrates the use of JUnit tests. The tests include a sample unit test and an
 * integration test.
 */
public class HelloApp {
    public static final int EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD = 2;
    public static final int EXIT_STATUS_HELLO_FAILED = 4;
    
    public static void main(String[] args) {
        try {
            // Your existing logic
            if (invalidCondition) {
                System.exit(EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD);
            }
            // Normal execution
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(EXIT_STATUS_HELLO_FAILED);
        }
    }
}
        Hello hi = new Hello();
        try {
            hi.setTimes(times);
        } catch (IllegalArgumentException e) {
            System.err.println("Something went wrong: " + e.getMessage());
            System.exit(EXIT_STATUS_HELLO_FAILED);
        }
        hi.sayHello(System.out);
    }

}
