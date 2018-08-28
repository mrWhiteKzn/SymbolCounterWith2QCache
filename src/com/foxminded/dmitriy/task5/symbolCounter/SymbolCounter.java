package com.foxminded.dmitriy.task5.symbolCounter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SymbolCounter {
    private static final int MAX_ENTRIES = 128;
    private LinkedHashMap<String, Map> cache = new LinkedHashMap<String, Map>(MAX_ENTRIES) {
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() >= MAX_ENTRIES;
        }
    };

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
