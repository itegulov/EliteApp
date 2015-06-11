package ru.ifmo.ctddev.elite.core;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Basic implementation of {@link StringCursor}.
 *
 * @author Daniyar Itegulov
 */
final class StringCursorImpl implements StringCursor {
    private ListIterator<String> iterator;

    public StringCursorImpl(ListIterator<String> iterator) {
        this.iterator = iterator;
    }

    @Override
    public String next() throws RemoteException {
        return iterator.next();
    }

    @Override
    public String previous() throws RemoteException {
        return iterator.previous();
    }

    @Override
    public boolean hasPrevious() throws RemoteException {
        return iterator.hasPrevious();
    }

    @Override
    public boolean hasNext() throws RemoteException {
        return iterator.hasNext();
    }

    @Override
    public void remove() throws RemoteException {
        iterator.remove();
    }
}
