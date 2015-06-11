package ru.ifmo.ctddev.elite.editor;

import ru.ifmo.ctddev.elite.core.StringCore;
import ru.ifmo.ctddev.elite.core.StringCursor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StringEditor {
    public static final int MAX_THREADS = 10;
    private final JFrame frame;
    private final JList<String> jList;
    private final JPanel buttonPanel;
    private final DefaultListModel<String> listModel;
    private StringCore stringCore;
    private final ExecutorService executorService;
    private StringCursor first;

    StringEditor() throws RemoteException{
        executorService = Executors.newFixedThreadPool(MAX_THREADS);
        try {
            stringCore = StringCore.getInstance();
        } catch (NotBoundException | MalformedURLException e) {
            connectingError();
        }
        frame = new JFrame(StringEditor.class.getSimpleName());
        JPanel mainPanel = new JPanel();
        buttonPanel = new JPanel();
        listModel = new DefaultListModel<>();
        jList = new JList<>(listModel);

        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (int i = 0; i < 10; i++) {
            listModel.addElement("hah " + i);
        }

        mainPanel.setLayout(new BorderLayout());
        buttonPanel.setLayout(new FlowLayout());
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(jList, BorderLayout.CENTER);

        createButton("Add", this::addElement, 'a');
        createButton("Remove", this::removeElement, 'm');
        createButton("Refresh", this::refresh, 'f');

        frame.getContentPane().add(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                executorService.shutdownNow();
            }
        });
        frame.pack();
        frame.setVisible(true);
        refresh();
    }

    private void createButton(String name, Runnable action, char mnemonic) {
        JButton ans = new JButton(name);
        ans.addActionListener(e -> action.run());
        ans.setMnemonic(mnemonic);
        buttonPanel.add(ans);
    }

    private void refresh() {
        executorService.submit(() -> {
            try {
                StringCursor stringCursor = stringCore.getAllStrings();
                List<String> list = new ArrayList<>();
                while (stringCursor.hasNext()) {
                    list.add(stringCursor.next());
                }
                first = stringCore.getAllStrings();
                SwingUtilities.invokeLater(() -> refreshUI(list));
            } catch (RemoteException e) {
                connectingError();
            }
        });
    }

    private void refreshUI(List<String> list) {
        listModel.removeAllElements();
        list.forEach(listModel::addElement);
    }

    private void removeElement() {
        final int index = jList.getSelectedIndex();
        executorService.submit(() -> {
            try {
                for (int i = 0; i <= index; i++) {
                    first.next();
                }
                stringCore.removeString(first);
                first = stringCore.getAllStrings();
                refresh();
            } catch (RemoteException e) {
                connectingError();
            }
        });
    }

    public void addElement() {
        String str = JOptionPane.showInputDialog(frame, "Enter your string", "Adding string", JOptionPane.PLAIN_MESSAGE);
        if (str == null)
            return;
        executorService.submit(() -> {
            try {
                stringCore.addString(str);
            } catch (RemoteException e) {
                connectingError();
            }
            refresh();
        });
    }

    public void connectingError() {
        JOptionPane.showMessageDialog(frame, "Troubles with connecting", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) throws RemoteException {
        new StringEditor();
    }
}