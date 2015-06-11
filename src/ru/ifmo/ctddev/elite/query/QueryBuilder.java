package ru.ifmo.ctddev.elite.query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zakhar Voit (zakharvoit@gmail.com)
 */
public class QueryBuilder {
    private final List<String> strings;

    public QueryBuilder() {
        strings = new ArrayList<>();
    }

    /**
     * Add new string.
     *
     * @param newString a string to add
     */
    public void add(String newString) {
        strings.add(newString);
    }

    /**
     * Create the query.
     *
     * @return the created query
     */
    public Query create() {
        // Copy the list because it can change
        final List<String> copy = new ArrayList<>(strings);
        return () -> copy;
    }

    public void clear() {
        strings.clear();
    }
}
