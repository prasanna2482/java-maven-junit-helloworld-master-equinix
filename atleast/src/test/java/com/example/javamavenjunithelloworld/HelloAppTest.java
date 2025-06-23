package com.example.javamavenjunithelloworld;

import org.junit.jupiter.api.*;
import java.io.*;
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
        System.setSecurityManager(new NoExitSecurityManager());
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        System.setSecurityManager(originalSecurityManager);
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    // Test cases that expect System.exit()
    @Test
    void testMain_ShouldExitWithZero() {
        try {
            HelloApp.main(new String[]{});
            fail("Expected System.exit() to be called");
        } catch (ExitException e) {
            assertEquals(0, e.status);
        }
    }

    @Test
    void testMainWithInvalidArgument_ShouldExitWithError() {
        try {
            HelloApp.main(new String[]{"invalid"});
            fail("Expected System.exit() to be called");
        } catch (ExitException e) {
            assertEquals(HelloApp.EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD, e.status);
            assertTrue(errContent.toString().contains("I don't understand"));
        }
    }

    // Test cases that shouldn't exit
    @Test
    void testMainWithValidArgument_ShouldNotExit() {
        HelloApp.main(new String[]{"2"}); // This shouldn't exit
        assertEquals("Hello!\nHello!\n", outContent.toString());
    }

    // Supporting classes
    private static class NoExitSecurityManager extends SecurityManager {
        @Override
        public void checkExit(int status) {
            throw new ExitException(status);
        }
    }

    private static class ExitException extends SecurityException {
        public final int status;
        public ExitException(int status) {
            this.status = status;
        }
    }
}
