package ru.ifmo.ctddev.elite.core;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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
    List<Integer> countStrings(Collection<String> collection) throws RemoteException;

    /**
     * Gets all requests, made to this database.
     * @return list of requests for this database
     * @throws RemoteException if some remote error has occurred
     */
    List<RequestHistory> getQueries() throws RemoteException;

    static StringCore getInstance() throws RemoteException, NotBoundException, MalformedURLException {
        return (StringCore) Naming.lookup("rmi://localhost/core");
    }
}
