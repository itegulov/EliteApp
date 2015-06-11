package ru.ifmo.ctddev.elite.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.*;

/**
 * Basic implementation of {@link StringCore}.
 *
 * @author Daniyar Itegulov
 */
final class StringCoreImpl implements StringCore {
    private LinkedList<String> database = new LinkedList<>();

    public StringCoreImpl(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            database.add(scanner.nextLine());
        }
    }

    @Override
    public StringCursor addString(String string) throws RemoteException {
        database.add(string);
        return new StringCursorImpl(database.listIterator(database.size() - 1));
    }

    @Override
    public void removeString(StringCursor cursor) throws RemoteException {
        cursor.remove();
    }

    @Override
    public StringCursor getAllStrings() throws RemoteException {
        return new StringCursorImpl(database.listIterator());
    }

    @Override
    public List<Boolean> isAllExists(Collection<String> collection) throws RemoteException {
        List<Boolean> list = new ArrayList<>();
        for (String string: collection) {
            boolean found = false;
            for (String data: database) {
                if (string.equals(data)) {
                    found = true;
                    break;
                }
            }
            list.add(found);
        }
        return list;
    }

    @Override
    public List<RequestHistory> getQueries() throws RemoteException {
        throw new UnsupportedOperationException();
    }
}
