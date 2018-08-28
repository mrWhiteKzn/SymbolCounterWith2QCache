package com.foxminded.dmitriy.task5.symbolCounter;

import java.util.Map;

public class ColumnFormatter {
    public String format(Map count) {
        StringBuilder result = new StringBuilder();
        count.forEach((k, v) -> result
                .append("\"")
                .append(k)
                .append("\"")
                .append(" - ")
                .append(v)
                .append("\n"));
        return result.toString();
    }
}
