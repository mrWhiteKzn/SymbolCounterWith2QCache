package com.foxminded.dmitriy.task5.symbolCounter.tests;

import com.foxminded.dmitriy.task5.symbolCounter.ColumnFormatter;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ColumnFormatterTest {
    private Map<String, Long> frequentChars;
    private ColumnFormatter formatter;

    @Before
    public void setup(){
        frequentChars = new LinkedHashMap<>();
        formatter = new ColumnFormatter();
    }

    @Test
    public void formatDefaultString() {
        frequentChars.put("h", 1L);
        frequentChars.put("e", 1L);
        frequentChars.put("l", 3L);
        frequentChars.put("o", 2L);
        frequentChars.put(" ", 1L);
        frequentChars.put("w", 1L);
        frequentChars.put("r", 1L);
        frequentChars.put("d", 1L);
        frequentChars.put("!", 1L);

        String actual = formatter.format(frequentChars);
        String expected =   "\"h\" - 1\n" +
                            "\"e\" - 1\n" +
                            "\"l\" - 3\n" +
                            "\"o\" - 2\n" +
                            "\" \" - 1\n" +
                            "\"w\" - 1\n" +
                            "\"r\" - 1\n" +
                            "\"d\" - 1\n" +
                            "\"!\" - 1\n";
        assertEquals(expected,actual);
    }

    @Test
    public void countShortString() {
        frequentChars.put("o", 1L);
        frequentChars.put("O", 1L);

        String actual = formatter.format(frequentChars);
        String expected =   "\"o\" - 1\n" +
                            "\"O\" - 1\n";

        assertEquals(expected,actual);
    }

    @Test
    public void countLongString() {
        frequentChars.put("J", 1L);
        frequentChars.put("A", 8L);
        frequentChars.put("V", 1L);
        frequentChars.put(" ", 8L);
        frequentChars.put("i", 1L);
        frequentChars.put("s", 1L);
        frequentChars.put("T", 2L);
        frequentChars.put("H", 1L);
        frequentChars.put("E", 3L);
        frequentChars.put("M", 3L);
        frequentChars.put("O", 3L);
        frequentChars.put("S", 1L);
        frequentChars.put("P", 3L);
        frequentChars.put("U", 2L);
        frequentChars.put("L", 2L);
        frequentChars.put("R", 3L);
        frequentChars.put("G", 5L);
        frequentChars.put("I", 1L);
        frequentChars.put("N", 2L);
        frequentChars.put("l", 2L);
        frequentChars.put("a", 2L);
        frequentChars.put("n", 2L);
        frequentChars.put("g", 3L);
        frequentChars.put("u", 2L);
        frequentChars.put("e", 1L);
        frequentChars.put("!", 1L);

        String actual = formatter.format(frequentChars);
        String expected =   "\"J\" - 1\n" +
                            "\"A\" - 8\n" +
                            "\"V\" - 1\n" +
                            "\" \" - 8\n" +
                            "\"i\" - 1\n" +
                            "\"s\" - 1\n" +
                            "\"T\" - 2\n" +
                            "\"H\" - 1\n" +
                            "\"E\" - 3\n" +
                            "\"M\" - 3\n" +
                            "\"O\" - 3\n" +
                            "\"S\" - 1\n" +
                            "\"P\" - 3\n" +
                            "\"U\" - 2\n" +
                            "\"L\" - 2\n" +
                            "\"R\" - 3\n" +
                            "\"G\" - 5\n" +
                            "\"I\" - 1\n" +
                            "\"N\" - 2\n" +
                            "\"l\" - 2\n" +
                            "\"a\" - 2\n" +
                            "\"n\" - 2\n" +
                            "\"g\" - 3\n" +
                            "\"u\" - 2\n" +
                            "\"e\" - 1\n" +
                            "\"!\" - 1\n";

        assertEquals(expected,actual);
    }

    @Test
    public void countCyrillicString() {
        frequentChars.put("М", 1L);
        frequentChars.put("а", 4L);
        frequentChars.put("м", 2L);
        frequentChars.put(",", 1L);
        frequentChars.put(" ", 3L);
        frequentChars.put("е", 1L);
        frequentChars.put("н", 1L);
        frequentChars.put("я", 1L);
        frequentChars.put("ф", 1L);
        frequentChars.put("у", 2L);
        frequentChars.put("р", 1L);
        frequentChars.put("б", 1L);
        frequentChars.put("и", 1L);
        frequentChars.put("л", 1L);

        String actual = formatter.format(frequentChars);
        String expected =   "\"М\" - 1\n" +
                            "\"а\" - 4\n" +
                            "\"м\" - 2\n" +
                            "\",\" - 1\n" +
                            "\" \" - 3\n" +
                            "\"е\" - 1\n" +
                            "\"н\" - 1\n" +
                            "\"я\" - 1\n" +
                            "\"ф\" - 1\n" +
                            "\"у\" - 2\n" +
                            "\"р\" - 1\n" +
                            "\"б\" - 1\n" +
                            "\"и\" - 1\n" +
                            "\"л\" - 1\n";

        assertEquals(expected,actual);
    }

    @Test
    public void countChineseString() {
        frequentChars.put("南", 2L);
        frequentChars.put("部", 2L);
        frequentChars.put("壮", 1L);
        frequentChars.put("语", 1L);
        frequentChars.put(",", 1L);
        frequentChars.put(" ", 1L);
        frequentChars.put("壯", 1L);
        frequentChars.put("語", 1L);

        String actual = formatter.format(frequentChars);
        String expected =   "\"南\" - 2\n" +
                            "\"部\" - 2\n" +
                            "\"壮\" - 1\n" +
                            "\"语\" - 1\n" +
                            "\",\" - 1\n" +
                            "\" \" - 1\n" +
                            "\"壯\" - 1\n" +
                            "\"語\" - 1\n";
        assertEquals(expected,actual);
    }

    @Test
    public void countSpecialSymbolsString() {
        frequentChars.put("|", 3L);
        frequentChars.put("*", 2L);
        frequentChars.put("^", 3L);
        frequentChars.put("(", 3L);
        frequentChars.put("@", 3L);
        frequentChars.put("#", 2L);
        frequentChars.put("$", 5L);
        frequentChars.put("!", 2L);
        frequentChars.put("%", 1L);
        frequentChars.put("_", 2L);

        String actual = formatter.format(frequentChars);
        String expected =   "\"|\" - 3\n" +
                            "\"*\" - 2\n" +
                            "\"^\" - 3\n" +
                            "\"(\" - 3\n" +
                            "\"@\" - 3\n" +
                            "\"#\" - 2\n" +
                            "\"$\" - 5\n" +
                            "\"!\" - 2\n" +
                            "\"%\" - 1\n" +
                            "\"_\" - 2\n";

        assertEquals(expected,actual);
    }

    @Test
    public void countUrlString() {
        frequentChars.put("h", 2L);
        frequentChars.put("t", 6L);
        frequentChars.put("p", 1L);
        frequentChars.put(":", 1L);
        frequentChars.put("/", 4L);
        frequentChars.put("g", 1L);
        frequentChars.put("i", 5L);
        frequentChars.put(".", 3L);
        frequentChars.put("f", 1L);
        frequentChars.put("o", 4L);
        frequentChars.put("x", 1L);
        frequentChars.put("m", 4L);
        frequentChars.put("n", 2L);
        frequentChars.put("d", 2L);
        frequentChars.put("e", 3L);
        frequentChars.put("c", 1L);
        frequentChars.put("u", 2L);
        frequentChars.put("a", 1L);
        frequentChars.put("D", 1L);
        frequentChars.put("r", 2L);
        frequentChars.put("y", 2L);
        frequentChars.put("W", 1L);
        frequentChars.put("s", 1L);
        frequentChars.put("b", 1L);
        frequentChars.put("l", 1L);
        frequentChars.put("C", 1L);

        String actual = formatter.format(frequentChars);
        String expected =   "\"h\" - 2\n" +
                            "\"t\" - 6\n" +
                            "\"p\" - 1\n" +
                            "\":\" - 1\n" +
                            "\"/\" - 4\n" +
                            "\"g\" - 1\n" +
                            "\"i\" - 5\n" +
                            "\".\" - 3\n" +
                            "\"f\" - 1\n" +
                            "\"o\" - 4\n" +
                            "\"x\" - 1\n" +
                            "\"m\" - 4\n" +
                            "\"n\" - 2\n" +
                            "\"d\" - 2\n" +
                            "\"e\" - 3\n" +
                            "\"c\" - 1\n" +
                            "\"u\" - 2\n" +
                            "\"a\" - 1\n" +
                            "\"D\" - 1\n" +
                            "\"r\" - 2\n" +
                            "\"y\" - 2\n" +
                            "\"W\" - 1\n" +
                            "\"s\" - 1\n" +
                            "\"b\" - 1\n" +
                            "\"l\" - 1\n" +
                            "\"C\" - 1\n";
        assertEquals(expected,actual);
    }
}