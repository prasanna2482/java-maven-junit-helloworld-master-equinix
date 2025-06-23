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
            HelloApp.main(new String[]{"abc"
