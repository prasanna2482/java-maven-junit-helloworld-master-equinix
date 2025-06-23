package com.example.javamavenjunithelloworld;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class HelloAppTest {
    private SecurityManager originalSecurityManager;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        originalSecurityManager = System.getSecurityManager();
        System.setSecurityManager(new TestingSecurityManager());
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        System.setSecurityManager(originalSecurityManager);
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testMain() {
        try {
            HelloApp.main(new String[]{});
            fail("Expected System.exit() to be called");
        } catch (TestingSecurityManager.TestExitException e) {
            assertEquals(0, e.getStatus());
        }
    }

    @Test
    void testMainWithValidNumericArgument() {
        try {
            HelloApp.main(new String[]{"2"});
            fail("Expected System.exit() to be called");
        } catch (TestingSecurityManager.TestExitException e) {
            assertEquals("Hello!\nHello!\n", outContent.toString());
        }
    }

    @Test
    void testMainWithInvalidNonNumericArgument() {
        try {
            HelloApp.main(new String[]{"abc"});
            fail("Expected System.exit() to be called");
        } catch (TestingSecurityManager.TestExitException e) {
            assertEquals(HelloApp.EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD, e.getStatus());
            assertTrue(errContent.toString().contains("I don't understand the parameter"));
            assertTrue(errContent.toString().contains("[abc]"));
        }
    }

    @Test
    void testMainWithArgumentCausingIllegalArgumentException() {
        int invalidValue = Hello.MAXIMUM_AMOUNT_OF_TIMES + 1;
        try {
            HelloApp.main(new String[]{String.valueOf(invalidValue)});
            fail("Expected System.exit() to be called");
        } catch (TestingSecurityManager.TestExitException e) {
            assertEquals(HelloApp.EXIT_STATUS_HELLO_FAILED, e.getStatus());
            assertTrue(errContent.toString().contains("Something went wrong"));
            assertTrue(errContent.toString().contains("no larger than"));
        }
    }

    @Test
    void testMainWithZeroArgument() {
        try {
            HelloApp.main(new String[]{"0"});
            fail("Expected System.exit() to be called");
        } catch (TestingSecurityManager.TestExitException e) {
            assertEquals("", outContent.toString());
        }
    }

    @Test
    void testMainWithMaximumValidArgument() {
        try {
            HelloApp.main(new String[]{String.valueOf(Hello.MAXIMUM_AMOUNT_OF_TIMES)});
            fail("Expected System.exit() to be called");
        } catch (TestingSecurityManager.TestExitException e) {
            String expectedOutput = "Hello!\n".repeat(Hello.MAXIMUM_AMOUNT_OF_TIMES);
            assertEquals(expectedOutput, outContent.toString());
        }
    }

    @Test
    void testStaticFields() {
        assertEquals(3, HelloApp.DEFAULT_TIMES);
        assertEquals(2, HelloApp.EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD);
        assertEquals(4, HelloApp.EXIT_STATUS_HELLO_FAILED);
    }
}
