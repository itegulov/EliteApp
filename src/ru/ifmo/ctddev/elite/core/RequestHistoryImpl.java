package ru.ifmo.ctddev.elite.core;

/**
 * @author Daniyar Itegulov
 */
final class RequestHistoryImpl implements RequestHistory {
    private String data;
    int count;

    public RequestHistoryImpl(String data) {
        this.data = data;
        count = 0;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public String getString() {
        return data;
    }
}
