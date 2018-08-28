package com.foxminded.dmitriy.task5.symbolCounter.tests;

import com.foxminded.dmitriy.task5.symbolCounter.SymbolCounter;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SymbolCounterTest {

    private SymbolCounter counter;

    @Before
    public void setup(){
        counter = new SymbolCounter();
    }

    @Test
    public void countDefaultString() {
        Map actual = counter.count("hello world!");
        Map<String, Long> expected = new LinkedHashMap<>();
        expected.put("h", 1L);
        expected.put("e", 1L);
        expected.put("l", 3L);
        expected.put("o", 2L);
        expected.put(" ", 1L);
        expected.put("w", 1L);
        expected.put("r", 1L);
        expected.put("d", 1L);
        expected.put("!", 1L);
        assertEquals(expected,actual);
    }

    @Test
    public void countShortString() {
        Map actual = counter.count("oO");
        Map<String, Long> expected = new LinkedHashMap<>();
        expected.put("o", 1L);
        expected.put("O", 1L);
        assertEquals(expected,actual);
    }

    @Test
    public void countLongString() {
        Map actual = counter.count("JAVA is THE MOST POPULAR PROGRAMMING language LANGUAGE lAnGuAgE!");
        Map<String, Long> expected = new LinkedHashMap<>();
        expected.put("J", 1L);
        expected.put("A", 8L);
        expected.put("V", 1L);
        expected.put(" ", 8L);
        expected.put("i", 1L);
        expected.put("s", 1L);
        expected.put("T", 2L);
        expected.put("H", 1L);
        expected.put("E", 3L);
        expected.put("M", 3L);
        expected.put("O", 3L);
        expected.put("S", 1L);
        expected.put("P", 3L);
        expected.put("U", 2L);
        expected.put("L", 2L);
        expected.put("R", 3L);
        expected.put("G", 5L);
        expected.put("I", 1L);
        expected.put("N", 2L);
        expected.put("l", 2L);
        expected.put("a", 2L);
        expected.put("n", 2L);
        expected.put("g", 3L);
        expected.put("u", 2L);
        expected.put("e", 1L);
        expected.put("!", 1L);
        assertEquals(expected,actual);
    }

    @Test
    public void countCyrillicString() {
        Map actual = counter.count("Мама, меня фура убила");
        Map<String, Long> expected = new LinkedHashMap<>();
        expected.put("М", 1L);
        expected.put("а", 4L);
        expected.put("м", 2L);
        expected.put(",", 1L);
        expected.put(" ", 3L);
        expected.put("е", 1L);
        expected.put("н", 1L);
        expected.put("я", 1L);
        expected.put("ф", 1L);
        expected.put("у", 2L);
        expected.put("р", 1L);
        expected.put("б", 1L);
        expected.put("и", 1L);
        expected.put("л", 1L);
        assertEquals(expected,actual);
    }

    @Test
    public void countChineseString() {
        Map actual = counter.count("南部壮语, 南部壯語");
        Map<String, Long> expected = new LinkedHashMap<>();
        expected.put("南", 2L);
        expected.put("部", 2L);
        expected.put("壮", 1L);
        expected.put("语", 1L);
        expected.put(",", 1L);
        expected.put(" ", 1L);
        expected.put("壯", 1L);
        expected.put("語", 1L);
        assertEquals(expected,actual);
    }

    @Test
    public void countSpecialSymbolsString() {
        Map actual = counter.count("|||**^(@@#$!$(%(#_@$$$!^_^");
        Map<String, Long> expected = new LinkedHashMap<>();
        expected.put("|", 3L);
        expected.put("*", 2L);
        expected.put("^", 3L);
        expected.put("(", 3L);
        expected.put("@", 3L);
        expected.put("#", 2L);
        expected.put("$", 5L);
        expected.put("!", 2L);
        expected.put("%", 1L);
        expected.put("_", 2L);
        assertEquals(expected,actual);
    }

    @Test
    public void countUrlString() {
        Map actual = counter.count("http://git.foxminded.com.ua/DmitriyWhite/symbolCounter");
        Map<String, Long> expected = new LinkedHashMap<>();
        expected.put("h", 2L);
        expected.put("t", 6L);
        expected.put("p", 1L);
        expected.put(":", 1L);
        expected.put("/", 4L);
        expected.put("g", 1L);
        expected.put("i", 5L);
        expected.put(".", 3L);
        expected.put("f", 1L);
        expected.put("o", 4L);
        expected.put("x", 1L);
        expected.put("m", 4L);
        expected.put("n", 2L);
        expected.put("d", 2L);
        expected.put("e", 3L);
        expected.put("c", 1L);
        expected.put("u", 2L);
        expected.put("a", 1L);
        expected.put("D", 1L);
        expected.put("r", 2L);
        expected.put("y", 2L);
        expected.put("W", 1L);
        expected.put("s", 1L);
        expected.put("b", 1L);
        expected.put("l", 1L);
        expected.put("C", 1L);
        assertEquals(expected,actual);
    }
}