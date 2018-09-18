package com.foxminded.dmitriy.task5.symbolCounter.tests;

import com.foxminded.dmitriy.task5.symbolCounter.SymbolCounter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TwoQCacheTest {

    private SymbolCounter symbolCounter;

    @Before
    public void setUp() {
        symbolCounter = new SymbolCounter();

        // Fill cache of the counter. Max
        for (int i = 0; i < 128; i++) {
            symbolCounter.count("SOME STRING FOR TESTING #" + i);
        }
    }

    @Test
    public void countWhenCacheAccelerates() {
        long start = System.nanoTime();
        symbolCounter.count("SOME STRING FOR TESTING #1");
        long periodWithCache = System.nanoTime() - start;

        start = System.nanoTime();
        symbolCounter.count("SOME STRING FOR TESTING #128");
        long periodWithoutCache = System.nanoTime() - start;

        assertTrue(periodWithCache < periodWithoutCache);
    }

    @Test
    public void countWhenCleaningCache() {
        // Add new String to cache which extrude stored one because of full cache
        symbolCounter.count("SOME STRING FOR TESTING #128");

        // Check time of counting of the cached String
        long start = System.nanoTime();
        symbolCounter.count("SOME STRING FOR TESTING #128");
        long periodNewCachedString = System.nanoTime() - start;

        // Check time of counting of the extruded String
        start = System.nanoTime();
        symbolCounter.count("SOME STRING FOR TESTING #0");
        long periodExtrudedString = System.nanoTime() - start;

        assertTrue(periodNewCachedString < periodExtrudedString);
    }
}