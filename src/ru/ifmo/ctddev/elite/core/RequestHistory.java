package ru.ifmo.ctddev.elite.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Represents one query type.
 *
 * @author Daniyar Itegulov
 */
public interface RequestHistory extends Remote {
    /**
     * @return count of queries for this request type
     */
    int getCount() throws RemoteException;

    /**
     * @return string, represented by this request
     */
    String getString() throws RemoteException;
}
