package ru.ifmo.ctddev.elite.query;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Provides a query submitting interface.
 *
 * @author Zakhar Voit (zakharvoit@gmail.com)
 */
public class QuerySubmitter {
    /**
     * Submit the query to the server.
     *
     * @param query a query
     */
    public void submit(Query query) {
        System.err.println("Querying " + query);
        throw new NotImplementedException();
    }
}
