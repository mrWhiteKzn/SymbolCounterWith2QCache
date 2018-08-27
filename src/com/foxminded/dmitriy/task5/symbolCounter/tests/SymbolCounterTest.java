package com.foxminded.dmitriy.task5.symbolCounter.tests;

import com.foxminded.dmitriy.task5.symbolCounter.SymbolCounter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SymbolCounterTest {

    private SymbolCounter counter;

    @Before
    public void setup(){
        counter = new SymbolCounter();
    }

    @Test
    public void getCountShortString() {
        String actual = counter.getCount("oO");
        String expected = "{O=1, o=1}";
        assertEquals(expected,actual);
    }

    @Test
    public void getCountLongString() {
        String actual = counter.getCount("Java is my favorite language");
        String expected = "{ =4, a=5, e=2, f=1, g=2, i=2, J=1, l=1, m=1, n=1, o=1, r=1, s=1, t=1, u=1, v=2, y=1}";
        assertEquals(expected,actual);
    }

    @Test
    public void getCountCyrillicString() {
        String actual = counter.getCount("Мама, меня фура убила");
        String expected = "{р=1,  =3, у=2, ф=1, ,=1, я=1, а=4, б=1, е=1, и=1, л=1, м=2, М=1, н=1}";
        assertEquals(expected,actual);
    }
}