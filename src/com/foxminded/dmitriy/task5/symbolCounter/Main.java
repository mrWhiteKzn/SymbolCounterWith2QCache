package com.foxminded.dmitriy.task5.symbolCounter;

public class Main {
    public static void main(String[] args) {
        SymbolCounter symbolCounter = new SymbolCounter();
        System.out.println( symbolCounter.count("hello world"  ).toString());
    }
}
