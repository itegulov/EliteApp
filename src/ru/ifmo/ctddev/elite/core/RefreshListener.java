package ru.ifmo.ctddev.elite.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Daniyar Itegulov
 */
public interface RefreshListener extends Remote {
    void onRefresh() throws RemoteException;
}
