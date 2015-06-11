package ru.ifmo.ctddev.elite.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Daniyar Itegulov
 */
public class CoreStarter {
    private final static int PORT = 1488;
    /**
     * Starts server with {@link StringCore} available at {@code "rmi://localhost/core"}.
     *
     * @param args {@code args[0]} must contain filename of database
     */
    public static void main(String[] args) {
        if (args == null || args.length != 1 || args[0] == null) {
            System.err.println("Usage: java CoreStarter <filename>");
            return;
        }
        StringCore stringCore = null;
        try {
            stringCore = new StringCoreImpl(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + args[0]);
            return;
        }
        try {
            UnicastRemoteObject.exportObject(stringCore, PORT);
            Naming.rebind("rmi://localhost/core", stringCore);
        } catch (RemoteException e) {
            System.err.println("Cannot export object: " + e.getMessage());
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL");
        }
    }
}
