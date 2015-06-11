package ru.ifmo.ctddev.elite.core;

/**
 * Represents one query type.
 *
 * @author Daniyar Itegulov
 */
public interface RequestHistory {
    /**
     * @return count of queries for this request type
     */
    int getCount();

    /**
     * @return string, represented by this request
     */
    String getString();
}
