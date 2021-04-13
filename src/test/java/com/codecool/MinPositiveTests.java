package com.codecool;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import cccr.CCCRTestExecutionListener;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({CCCRTestExecutionListener.class})
class MinPositiveTests {

    @Test
    public void shouldReturnTheSmaller() {
        int expected0 = 3;
        int actual0 = Challenge.minPositive(3,5);
        assertEquals(expected0, actual0);
        int expected1 = 5;
        int actual1 = Challenge.minPositive(333,5);
        assertEquals(expected1, actual1);
    }

    @Test
    public void shouldReturnTheNumber() {
        int expected0 = 42;
        int actual0 = Challenge.minPositive(42,42);
        assertEquals(expected0, actual0);
    }

    @Test
    public void shouldReturnThePositiveOne() {
        int expected0 = 5;
        int actual0 = Challenge.minPositive(-3,5);
        assertEquals(expected0, actual0);
        int expected1 = 1;
        int actual1 = Challenge.minPositive(1,-5);
        assertEquals(expected1, actual1);
    }

    @Test
    public void shouldReturnTheNonZero() {
        int expected0 = 15;
        int actual0 = Challenge.minPositive(0,15);
        assertEquals(expected0, actual0);
        int expected1 = 7;
        int actual1 = Challenge.minPositive(7,0);
        assertEquals(expected1, actual1);
    }

    @Test
    public void shouldReturnMinusOne() {
        int expected0 = -1;
        int actual0 = Challenge.minPositive(-3,-5);
        assertEquals(expected0, actual0);
        int expected1 = -1;
        int actual1 = Challenge.minPositive(-13,-15);
        assertEquals(expected1, actual1);
    }
}