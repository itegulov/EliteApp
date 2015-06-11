package ru.ifmo.ctddev.elite.core;

import ru.ifmo.ctddev.elite.query.Query;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

/**
 * @author Daniyar Itegulov
 */
public interface StringCore extends Remote {
    /**
     * Simply adds provided string to database.
     *
     * @param string string to add
     * @return cursor on added string
     * @throws RemoteException if some remote error has occurred
     * @see StringCursor
     */
    StringCursor addString(String string) throws RemoteException;

    /**
     * Removes string, pointed by cursor, from database.
     * @param cursor cursor, which points on element to be deleted
     * @throws RemoteException if some remote error has occurred
     * @see StringCursor
     */
    void removeString(StringCursor cursor) throws RemoteException;

    /**
     * Gets cursor on first element in list of all string from database.
     * @return cursor for full list of string in database
     * @throws RemoteException if some remote error has occurred
     */
    StringCursor getAllStrings() throws RemoteException;

    /**
     * Checks if all strings from {@code collection} exist in database.
     * @param collection collection, containing strings to be checked
     * @return list of results for strings in collection
     * @throws RemoteException if some remote error has occurred
     */
    List<Boolean> isAllExists(Collection<String> collection) throws RemoteException;

    /**
     * Gets all queries, made to this database.
     * @return list of queries for this database
     * @throws RemoteException if some remote error has occurred
     */
    List<Query> getQueries() throws RemoteException;
}
