package ru.ifmo.ctddev.elite.query;

import ru.ifmo.ctddev.elite.core.StringCore;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Provides a query submitting interface.
 *
 * @author Zakhar Voit (zakharvoit@gmail.com)
 */
public class QuerySubmitter {
    /**
     * Submit the query to the server.
     *
     * @param query a query
     */
    public void submit(Component parent, Query query) {
        new Thread(() -> {
            try {
                StringCore core = StringCore.getInstance();
                List<String> queriedStrings = query.queriedStrings();
                List<Integer> countStrings = core.countStrings(queriedStrings);
                SwingUtilities.invokeLater(() -> {
                    ResponseUI ui = new ResponseUI(queriedStrings, countStrings);
                    ui.start();
                });
            } catch (RemoteException | NotBoundException | MalformedURLException e) {
                SwingUtilities.invokeLater(() -> showAlert(parent));
            }
        }).start();
    }

    private void showAlert(Component parent) {
        JOptionPane.showMessageDialog(parent, "Network error", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
