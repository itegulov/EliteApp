package ru.ifmo.ctddev.elite.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Daniyar Itegulov
 */
public class CoreStarter {
    private final static int PORT = 8841;
    private final static int RMI_DEFAULT_PORT = 1099;
    private static final Registry registry; //Save it in static field, so it won't be garbage-collected
    static {
        try {
            registry = LocateRegistry.createRegistry(RMI_DEFAULT_PORT);
        } catch (RemoteException e) {
            System.err.println("Couldn't create regitstry");
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
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
        StringCore stringCore;
        File file = new File(args[0]);
        try {
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Couldn't create file");
            return;
        }
        try {
            stringCore = new StringCoreImpl(new File(args[0]), PORT);
        } catch (FileNotFoundException e) {
            System.err.println("File not found for read: " + args[0]);
            return;
        }

        try {
            System.out.println("Starting server...");
            UnicastRemoteObject.exportObject(stringCore, PORT);
            Naming.rebind("rmi://localhost/core", stringCore);
            System.out.println("Server started successfully");
        } catch (RemoteException e) {
            System.err.println("Cannot export object: " + e.getMessage());
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Closing server...");
            try (PrintWriter pw = new PrintWriter(new File(args[0]))) {
                StringCursor cursor = stringCore.getAllStrings();
                while (cursor.hasNext()) {
                    String string = cursor.next();
                    System.out.println("Writing to DB: " + string);
                    pw.println(string);
                }
            } catch (RemoteException e) {
                System.err.println("Couldn't connect to StringCore");
            } catch (FileNotFoundException e) {
                System.err.println("File not found for write: " + args[0]);
            }
        }));
    }
}
