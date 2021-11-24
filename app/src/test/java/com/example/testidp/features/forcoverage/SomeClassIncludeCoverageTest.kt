package com.example.testidp.features.forcoverage

import junit.framework.Assert.assertEquals
import org.junit.Test

class SomeClassIncludeCoverageTest {
    @Test
    fun addition() {
        assertEquals(8, SomeClassIncludeCoverage().addition(3, 5))
    }

    @Test
    fun subtraction() {
        assertEquals(2, SomeClassIncludeCoverage().subtraction(6, 4))
    }

    @Test
    fun multiplication() {
        assertEquals(24, SomeClassIncludeCoverage().multiplication(6, 4))
    }

    @Test
    fun division() {
        assertEquals(3, SomeClassIncludeCoverage().division(12, 4))
    }
}