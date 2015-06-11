package ru.ifmo.ctddev.elite.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Daniyar Itegulov
 */
final class QueryEngine {
    private Map<String, RequestHistory> map = new HashMap<>();

    public void addQuery(String data) {
        if (!map.containsKey(data)) {
            map.put(data, new RequestHistoryImpl(data));
        }
    }
}
