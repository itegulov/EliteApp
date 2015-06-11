package ru.ifmo.ctddev.elite.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Basic implementation of {@link StringCore}.
 *
 * @author Daniyar Itegulov
 */
final class StringCoreImpl implements StringCore {
    private LinkedList<String> database = new LinkedList<>();
    private QueryEngine queryEngine = new QueryEngine();
    private int port;

    public StringCoreImpl(File file, int port) throws FileNotFoundException {
        this.port = port;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            database.add(scanner.nextLine());
        }
    }

    @Override
    public StringCursor addString(String string) throws RemoteException {
        database.add(string);
        StringCursor cursor = new StringCursorImpl(database.listIterator(database.size() - 1));
        UnicastRemoteObject.exportObject(cursor, port);
        return cursor;
    }

    @Override
    public void removeString(StringCursor cursor) throws RemoteException {
        cursor.remove();
    }

    @Override
    public StringCursor getAllStrings() throws RemoteException {
        StringCursor cursor = new StringCursorImpl(database.listIterator());
        UnicastRemoteObject.exportObject(cursor, port);
        return cursor;
    }

    @Override
    public List<Integer> countStrings(Collection<String> collection) throws RemoteException {
        List<Integer> list = new ArrayList<>();
        for (String string: collection) {
            int count = 0;
            queryEngine.addQuery(string);
            for (String data: database) {
                if (string.equals(data)) {
                    count++;
                }
            }
            list.add(count);
        }
        return list;
    }

    @Override
    public List<RequestHistory> getQueries() throws RemoteException {
        return queryEngine.getAllRequest();
    }
}
