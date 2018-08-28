package com.foxminded.dmitriy.task5.symbolCounter;

public class Main {

    public static void main(String[] args) {
        SymbolCounter symbolCounter = new SymbolCounter();
        String result = symbolCounter.count("Мама, меня фура убила");
        System.out.println(result);
    }
}
