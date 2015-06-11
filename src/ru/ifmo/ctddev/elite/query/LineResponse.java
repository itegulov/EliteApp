package ru.ifmo.ctddev.elite.query;

import java.util.List;

/**
 * @author Zakhar Voit (zakharvoit@gmail.com)
 */
public abstract class LineResponse {
    public abstract boolean isPresent();
    public abstract List<Integer> getOccurences();
}
