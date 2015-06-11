package ru.ifmo.ctddev.elite.core;

import java.rmi.RemoteException;

/**
 * @author Daniyar Itegulov
 */
final class RequestHistoryImpl implements RequestHistory {
    private String data;
    int count;

    public RequestHistoryImpl(String data) {
        this.data = data;
        count = 0;
    }

    @Override
    public int getCount() throws RemoteException {
        return count;
    }

    @Override
    public String getString() throws RemoteException {
        return data;
    }
}
