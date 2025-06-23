package com.example.javamavenjunithelloworld;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class HelloTest {

    @Test
    void testDefaultConstructor() {
        Hello hello = new Hello();
        assertEquals(1, hello.times); // Testing default times value
    }

    @Test
    void testSetTimesWithValidValues() {
        Hello hello = new Hello();
        
        // Test minimum valid value
        hello.setTimes(0);
        assertEquals(0, hello.times);
        
        // Test maximum valid value
        hello.setTimes(Hello.MAXIMUM_AMOUNT_OF_TIMES);
        assertEquals(Hello.MAXIMUM_AMOUNT_OF_TIMES, hello.times);
        
        // Test a middle value
        hello.setTimes(10);
        assertEquals(10, hello.times);
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
    void testSayHelloWithDefaultTimes() {
        Hello hello = new Hello();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        hello.sayHello(System.out);
        
        assertEquals("Hello!\n", outContent.toString());
    }

    @Test
    void testSayHelloWithZeroTimes() {
        Hello hello = new Hello();
        hello.setTimes(0);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        hello.sayHello(System.out);
        
        assertEquals("", outContent.toString());
    }

    @Test
    void testSayHelloWithMultipleTimes() {
        Hello hello = new Hello();
        hello.setTimes(3);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        hello.sayHello(System.out);
        
        assertEquals("Hello!\nHello!\nHello!\n", outContent.toString());
    }

    @Test
    void testSayHelloWithCustomPrintStream() {
        Hello hello = new Hello();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream customPrintStream = new PrintStream(outContent);
        
        hello.sayHello(customPrintStream);
        
        assertEquals("Hello!\n", outContent.toString());
    }
}
