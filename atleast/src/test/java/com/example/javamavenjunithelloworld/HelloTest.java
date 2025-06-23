package com.example.javamavenjunithelloworld;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class HelloTest {

    @Test
    void testDefaultBehavior() {
        Hello hello = new Hello();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        hello.sayHello(new PrintStream(outContent));
        assertEquals("Hello!\n", outContent.toString());
    }

    @Test
    void testSetTimesWithValidValues() {
        Hello hello = new Hello();
        
        // Test minimum valid value
        hello.setTimes(0);
        ByteArrayOutputStream outContent0 = new ByteArrayOutputStream();
        hello.sayHello(new PrintStream(outContent0));
        assertEquals("", outContent0.toString());
        
        // Test maximum valid value
        hello.setTimes(Hello.MAXIMUM_AMOUNT_OF_TIMES);
        ByteArrayOutputStream outContentMax = new ByteArrayOutputStream();
        hello.sayHello(new PrintStream(outContentMax));
        String expectedMax = "Hello!\n".repeat(Hello.MAXIMUM_AMOUNT_OF_TIMES);
        assertEquals(expectedMax, outContentMax.toString());
        
        // Test a middle value
        hello.setTimes(3);
        ByteArrayOutputStream outContent3 = new ByteArrayOutputStream();
        hello.sayHello(new PrintStream(outContent3));
        assertEquals("Hello!\nHello!\nHello!\n", outContent3.toString());
    }

    @Test
    void testSetTimesWithInvalidValues() {
        Hello hello = new Hello();
        
        // Test negative value
        IllegalArgumentException negativeEx = assertThrows(IllegalArgumentException.class, 
            () -> hello.setTimes(-1));
        assertTrue(negativeEx.getMessage().contains("positive number"));
        
        // Test value above maximum
        IllegalArgumentException aboveMaxEx = assertThrows(IllegalArgumentException.class, 
            () -> hello.setTimes(Hello.MAXIMUM_AMOUNT_OF_TIMES + 1));
        assertTrue(aboveMaxEx.getMessage().contains("no larger than"));
    }

    @Test
    void testSayHelloWithDifferentTimes() {
        Hello hello = new Hello();
        
        // Test default (1 time)
        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        hello.sayHello(new PrintStream(out1));
        assertEquals("Hello!\n", out1.toString());
        
        // Test 0 times
        hello.setTimes(0);
        ByteArrayOutputStream out0 = new ByteArrayOutputStream();
        hello.sayHello(new PrintStream(out0));
        assertEquals("", out0.toString());
        
        // Test multiple times
        hello.setTimes(2);
        ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        hello.sayHello(new PrintStream(out2));
        assertEquals("Hello!\nHello!\n", out2.toString());
    }
}
