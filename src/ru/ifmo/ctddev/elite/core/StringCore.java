package ru.ifmo.ctddev.elite.core;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

/**
 * @author Daniyar Itegulov
 */
public interface StringCore {
    StringCursor addString(String string) throws RemoteException;

    void removeString(StringCursor cursor) throws RemoteException;

    StringCursor getAllStrings() throws RemoteException;

    List<Boolean> isAllExists(Collection<String> collection) throws RemoteException;
}
