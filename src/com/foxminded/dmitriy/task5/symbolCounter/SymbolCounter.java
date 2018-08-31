package com.foxminded.dmitriy.task5.symbolCounter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SymbolCounter {
    private static final int MAX_CACHE_SIZE = 128;
    private TwoQCache cache = new TwoQCache<String, Map>(MAX_CACHE_SIZE);

    public String count(String input) {
        if (cache.get(input) == null) {
            Map<String, Long> frequentChars = Arrays.stream(
                    input.split("")).collect(
                    Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()));

            cache.put(input, frequentChars);
            System.out.println("no cache");
            return frequentChars.toString();
        }
        System.out.println("from cache");
        return cache.get(input).toString();
    }
}
