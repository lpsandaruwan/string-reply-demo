package com.beta.replyservice.rule.operation;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OperationsTest {

    @Autowired
    private Operations operations;

    @Test
    void testToReverseString() {
        String input = "hello";
        String expectedResult = "olleh";
        assertEquals(expectedResult, operations.toReverseString(input));
    }

    @Test
    void testToMd5String() {
        String input = "hello";
        String expectedResult = "5d41402abc4b2a76b9719d911017c592"; // MD5 hash of "hello"
        assertEquals(expectedResult, operations.toMd5String(input));
    }

    @Test
    void testToUpperCase() {
        String input = "hello";
        String expectedResult = "HELLO";
        assertEquals(expectedResult, operations.toUpperCase(input));
    }
}
