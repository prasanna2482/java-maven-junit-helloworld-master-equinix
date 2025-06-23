package com.example.javamavenjunithelloworld;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class HelloAppTest {
    private SecurityManager originalSecurityManager;

    @BeforeEach
    void setUp() {
        originalSecurityManager = System.getSecurityManager();
        System.setSecurityManager(new TestingSecurityManager());
    }

    @AfterEach
    void tearDown() {
        System.setSecurityManager(originalSecurityManager);
    }

    @Test
    void testMain() {
        try {
            HelloApp.main(new String[]{});
            fail("Expected System.exit() to be called");
        } catch (TestingSecurityManager.TestExitException e) {
            assertEquals(0, e.getStatus()); // Verify expected exit code
        }
    }
}

class HelloAppTest {

    @Test
    void testMainWithNoArguments() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        HelloApp.main(new String[]{});
        
        assertEquals("Hello!\nHello!\nHello!\n", outContent.toString());
    }

    @Test
    void testMainWithValidNumericArgument() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        HelloApp.main(new String[]{"2"});
        
        assertEquals("Hello!\nHello!\n", outContent.toString());
    }

    @Test
    void testMainWithInvalidNonNumericArgument() {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        
        assertDoesNotThrow(() -> HelloApp.main(new String[]{"abc"}));
        
        assertTrue(errContent.toString().contains("I don't understand the parameter"));
        assertTrue(errContent.toString().contains("[abc]"));
    }

    @Test
    void testMainWithArgumentCausingIllegalArgumentException() {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        
        // Using a value larger than MAXIMUM_AMOUNT_OF_TIMES
        int invalidValue = Hello.MAXIMUM_AMOUNT_OF_TIMES + 1;
        assertDoesNotThrow(() -> HelloApp.main(new String[]{String.valueOf(invalidValue)}));
        
        assertTrue(errContent.toString().contains("Something went wrong"));
        assertTrue(errContent.toString().contains("no larger than"));
    }

    @Test
    void testMainWithZeroArgument() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        HelloApp.main(new String[]{"0"});
        
        assertEquals("", outContent.toString());
    }

    @Test
    void testMainWithMaximumValidArgument() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        HelloApp.main(new String[]{String.valueOf(Hello.MAXIMUM_AMOUNT_OF_TIMES)});
        
        int expectedLines = Hello.MAXIMUM_AMOUNT_OF_TIMES;
        String expectedOutput = "Hello!\n".repeat(expectedLines);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testStaticFields() {
        assertEquals(3, HelloApp.DEFAULT_TIMES);
        assertEquals(2, HelloApp.EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD);
        assertEquals(4, HelloApp.EXIT_STATUS_HELLO_FAILED);
    }
}
