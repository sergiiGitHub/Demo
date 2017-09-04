package com.example.sergii.geofirebase;

import com.example.sergii.geofirebase.utils.UtilsTransform;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void UtilsTransformTest() throws Exception {

        String[] prohibitSymbol = { ".", "#", "$", "[", "]", "#"};
        String[] samles = { "m.s.m", "m[s]m", "m.S#$.f" };

        String res;
        for( int i = 0; i < samles.length; ++i) {
            res = UtilsTransform.getTransformedEmailOld(samles[i]);
            assertFalse(res == null);

            for( int j = 0; j < prohibitSymbol.length; ++j) {
                assertFalse(res.contains(prohibitSymbol[j]));
            }
        }
    }
}