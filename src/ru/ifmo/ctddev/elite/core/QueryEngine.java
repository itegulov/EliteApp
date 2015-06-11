package ru.ifmo.ctddev.elite.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Daniyar Itegulov
 */
final class QueryEngine {
    private Map<String, RequestHistoryImpl> map = new HashMap<>();

    public void addQuery(String data) {
        RequestHistoryImpl requestHistory;
        if (!map.containsKey(data)) {
            requestHistory = new RequestHistoryImpl(data);
            map.put(data, requestHistory);
        } else {
            requestHistory = map.get(data);
        }
        requestHistory.count++;
    }

    public List<RequestHistory> getAllRequest() {
        return map.values().stream().collect(Collectors.toList());
    }
}
