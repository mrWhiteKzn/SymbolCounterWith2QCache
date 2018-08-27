package com.foxminded.dmitriy.task5.symbolCounter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SymbolCounter {
    Map<String, Map> cache = new HashMap<>();

    public String getCount(String input) {
        if (!cache.containsKey(input)) {
            addToCache(input);
        }
        return cache.get(input).toString();
    }

    private void addToCache(String input) {
        Map<String, Long> frequentChars = Arrays.stream(
                input.split("")).collect(
                Collectors.groupingBy(c -> c, Collectors.counting()));
        cache.put(input, frequentChars);
    }
}
