package ru.ifmo.ctddev.elite.core;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;

/**
 * @author Daniyar Itegulov
 */
public interface StringCursor extends Remote {
    /**
     * Moves cursor on next element.
     *
     * @return next string, pointed by this cursor now.
     * @throws RemoteException if some remote error has occurred
     * @throws NoSuchElementException if there is no next element
     */
    String next() throws RemoteException;

    /**
     * Checks if there is next element.
     *
     * @return {@code true} if there is next element, {@code false} otherwise.
     * @throws RemoteException if some remote error has occurred
     */
    boolean hasNext() throws RemoteException;

    /**
     * Removes appropriate string from this database. Has no effect, if {@code RemoteException}
     * occurs.
     *
     * @throws RemoteException if some remote error has occurred.
     * @throws NoSuchElementException if there is no element, pointed by this cursor
     */
    void remove() throws RemoteException;
}
