package com.foxminded.dmitriy.task5.symbolCounter;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        SymbolCounter symbolCounter = new SymbolCounter();
        ColumnFormatter formatter = new ColumnFormatter();
        Map charsCount = symbolCounter.count("hello world");
        String result = formatter.format(charsCount);
        System.out.println(result);
    }
}
