package com.example.javamavenjunithelloworld;

import org.junit.jupiter.api.Test;

public class HelloAppTest {

    @Test
    public void testMainWithValidArg() {
        HelloApp.main(new String[]{"2"});  // should succeed
    }

    @Test
    public void testMainWithInvalidArg() {
        try {
            HelloApp.main(new String[]{"notanumber"});
        } catch (Exception ignored) {
            // Expect System.exit, may terminate test framework depending on runner
        }
    }

    @Test
    public void testMainWithNoArgs() {
        HelloApp.main(new String[]{});  // uses DEFAULT_TIMES = 3
    }

    @Test
    public void testMainWithTooHighValue() {
        try {
            HelloApp.main(new String[]{"100"});  // triggers IllegalArgumentException
        } catch (Exception ignored) {
            // Expect System.exit or error message
        }
    }
}
