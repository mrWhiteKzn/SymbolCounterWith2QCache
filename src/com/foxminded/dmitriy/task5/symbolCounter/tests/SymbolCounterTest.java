package com.foxminded.dmitriy.task5.symbolCounter.tests;

import com.foxminded.dmitriy.task5.symbolCounter.SymbolCounter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SymbolCounterTest {

    private SymbolCounter counter;

    @Before
    public void setup() {
        counter = new SymbolCounter();
    }

    @Test
    public void countDefaultString() {
        String actual = counter.count("hello world!");
        String expected = "{h=1, e=1, l=3, o=2,  =1, w=1, r=1, d=1, !=1}";
        assertEquals(expected, actual);
    }


    @Test
    public void countSpecialSymbolsString() {
        String actual = counter.count("|||**^(@@#$!$(%(#_@$$$!^_^");
        String expected = "{|=3, *=2, ^=3, (=3, @=3, #=2, $=5, !=2, %=1, _=2}";
        assertEquals(expected, actual);
    }

    @Test
    public void countUrlString() {
        String actual = counter.count("http://git.foxminded.com.ua/DmitriyWhite/symbolCounter");
        String expected = "{h=2, t=6, p=1, :=1, /=4, g=1, i=5, .=3, f=1, o=4, x=1, m=4, n=2, d=2, e=3, c=1, u=2, a=1, D=1, r=2, y=2, W=1, s=1, b=1, l=1, C=1}";
        assertEquals(expected, actual);
    }

    @Test
    public void getCountShortString() {
        String actual = counter.count("oO");
        String expected = "{o=1, O=1}";
        assertEquals(expected, actual);
    }

    @Test
    public void getCountLongString() {
        String actual = counter.count("Java is my favorite language");
        String expected = "{J=1, a=5, v=2,  =4, i=2, s=1, m=1, y=1, f=1, o=1, r=1, t=1, e=2, l=1, n=1, g=2, u=1}";
        assertEquals(expected, actual);
    }

    @Test
    public void countChineseString() {
        String actual = counter.count("南部壮语, 南部壯語");
        String expected = "{南=2, 部=2, 壮=1, 语=1, ,=1,  =1, 壯=1, 語=1}";
        assertEquals(expected, actual);
    }

    @Test
    public void getCountCyrillicString() {
        String actual = counter.count("Не плачь, Деметра и отойди от котёнка!");
        String expected = "{Н=1, е=3,  =6, п=1, л=1, а=3, ч=1, ь=1, ,=1, Д=1, м=1, т=4, р=1, и=2, о=4, й=1, д=1, к=2, ё=1, н=1, !=1}";
        assertEquals(expected, actual);
    }
}