package ru.ifmo.ctddev.elite.editor;

import ru.ifmo.ctddev.elite.core.StringCore;
import ru.ifmo.ctddev.elite.core.StringCursor;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StringEditor {
    public static final int MAX_THREADS = 10;
    JFrame frame;
    JList jList;
    JButton addButton, removeButton, refreshButton;
    JPanel mainPanel, buttonPanel;
    DefaultListModel<String> listModel;
    StringCore stringCore;
    ExecutorService executorService;
    StringCursor first;

    StringEditor() throws RemoteException{
        executorService = Executors.newFixedThreadPool(MAX_THREADS);
        /*try {
            stringCore = (StringCore) Naming.lookup("rmi://localhost/core");
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }*/
        frame = new JFrame(StringEditor.class.getSimpleName());
        mainPanel = new JPanel();
        buttonPanel = new JPanel();
        listModel = new DefaultListModel<>();
        jList = new JList(listModel);

        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (int i = 0; i < 10; i++) {
            listModel.addElement("hah " + i);
        }

        mainPanel.setLayout(new BorderLayout());
        buttonPanel.setLayout(new FlowLayout());
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(jList, BorderLayout.CENTER);
        addButton = new JButton("add");
        addButton.addActionListener(e -> addElement());
        removeButton = new JButton("remove");
        removeButton.addActionListener(e -> removeElements());
        refreshButton = new JButton("refresh");
        refreshButton.addActionListener(e -> refresh());

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        frame.getContentPane().add(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void refresh() {
        executorService.submit(() -> {
            try {
                StringCursor stringCursor = stringCore.getAllStrings();
                first = stringCursor;
                List<String> list = new ArrayList<>();
                while (stringCursor.hasNext()) {
                    list.add(stringCursor.next());
                }
                SwingUtilities.invokeLater(() -> refreshUI(list));
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        });
    }

    private void refreshUI(List<String> list) {
        listModel.removeAllElements();
        list.forEach(listModel::addElement);
    }

    private void removeElements() {
        int index = jList.getSelectedIndex();
        executorService.submit(() -> {
            try {
                for (int i = 0; i < index; i++) {
                    first.next();
                }

            } catch (RemoteException e) {
                e.printStackTrace();
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
                e.printStackTrace();
            }
        });
        refresh();
    }


    public static void main(String[] args) throws RemoteException {
        new StringEditor();
    }
}