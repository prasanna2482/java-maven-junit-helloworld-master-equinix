package com.example.javamavenjunithelloworld;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class HelloTest {

    @Test
    public void testSayHelloOnce() {
        Hello hello = new Hello();
        hello.setTimes(1);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        hello.sayHello(new PrintStream(out));

        assertEquals("Hello!\n", out.toString());
    }

    @Test
    public void testSayHelloMultipleTimes() {
        Hello hello = new Hello();
        hello.setTimes(3);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        hello.sayHello(new PrintStream(out));

        assertEquals("Hello!\nHello!\nHello!\n", out.toString());
    }

    @Test
    public void testSetTimesTooHigh() {
        Hello hello = new Hello();
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> hello.setTimes(21)
        );
        assertTrue(thrown.getMessage().contains("positive number no larger than"));
    }

    @Test
    public void testSetTimesNegative() {
        Hello hello = new Hello();
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> hello.setTimes(-1)
        );
        assertTrue(thrown.getMessage().contains("positive number no larger than"));
    }
}
