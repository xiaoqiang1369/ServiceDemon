package com.example.leon.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        TestNull testNull = new TestNull();
        testNull.testNull("222");
        assertEquals(4, 2 + 2);
    }
}