package com.foxminded.dmitriy.task5.symbolCounter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SymbolCounter {
    Map<String, Map> cache = new HashMap<>();

    public Map count(String input) {
        if (!cache.containsKey(input)) {
            Map<String, Long> frequentChars = Arrays.stream(
                    input.split("")).collect(
                    Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()));

            addToCache(input, frequentChars);
            return frequentChars;
        }
        return cache.get(input);
    }

    private void addToCache(String input, Map<String, Long> frequentChars) {
        cache.put(input, frequentChars);
    }
}
