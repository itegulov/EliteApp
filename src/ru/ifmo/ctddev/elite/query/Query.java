package ru.ifmo.ctddev.elite.query;

import java.util.List;
import java.util.Set;

/**
 * @author Zakhar Voit (zakharvoit@gmail.com)
 */
public interface Query {
    /**
     * Get list of the queried strings.
     *
     * @return the queried list
     */
    List<String> queriedStrings();
}
