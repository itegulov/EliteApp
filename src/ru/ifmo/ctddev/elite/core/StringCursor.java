package ru.ifmo.ctddev.elite.core;

import java.rmi.RemoteException;

/**
 * @author Daniyar Itegulov
 */
public interface StringCursor {
    void next() throws RemoteException;

    boolean hasNext() throws RemoteException;

    String get() throws RemoteException;

    void remove() throws RemoteException;
}
