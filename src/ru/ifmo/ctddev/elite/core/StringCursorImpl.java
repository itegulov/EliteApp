package ru.ifmo.ctddev.elite.core;

import java.rmi.RemoteException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Basic implementation of {@link StringCursor}.
 *
 * @author Daniyar Itegulov
 */
final class StringCursorImpl implements StringCursor {
    private List<String> database;
    private int index;

    public StringCursorImpl(List<String> database) {
        this.database = database;
        index = 0;
    }

    @Override
    public void next() throws RemoteException {
        if (index >= database.size() - 1) {
            throw new NoSuchElementException();
        }
        index++;
    }

    @Override
    public boolean hasNext() throws RemoteException {
        return index <= database.size() - 1;
    }

    @Override
    public String get() throws RemoteException {
        try {
            return database.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() throws RemoteException {
        try {
            database.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }
}
