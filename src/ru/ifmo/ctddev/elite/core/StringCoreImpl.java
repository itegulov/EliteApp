package ru.ifmo.ctddev.elite.core;

import ru.ifmo.ctddev.elite.query.Query;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Basic implementation of {@link StringCore}.
 *
 * @author Daniyar Itegulov
 */
final class StringCoreImpl implements StringCore {
    private List<String> database = new ArrayList<>();

    public StringCoreImpl(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            database.add(scanner.nextLine());
        }
    }

    @Override
    public StringCursor addString(String string) throws RemoteException {
        return null;
    }

    @Override
    public void removeString(StringCursor cursor) throws RemoteException {

    }

    @Override
    public StringCursor getAllStrings() throws RemoteException {
        return null;
    }

    @Override
    public List<Boolean> isAllExists(Collection<String> collection) throws RemoteException {
        return null;
    }

    @Override
    public List<Query> getQueries() throws RemoteException {
        return null;
    }
}
