package ru.ifmo.ctddev.elite.query;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Querying UI main window.
 *
 * @author Zakhar Voit (zakharvoit@gmail.com)
 */
public class QueryUI extends JFrame {
    private JList<String> rowsList;
    private JPanel rootPanel;
    private JTextField newRowInsertText;
    private JButton queryButton;
    private JButton clearButton;
    private final QueryBuilder queryBuilder;
    private final QuerySubmitter querySubmitter;
    private DefaultListModel<String> rowsModel;

    /**
     * Create new UI for building the query using <code>queryBuilder</code>.
     * And for submitting using <code>querySubmitter</code>
     *
     * @param queryBuilder a builder to use for creating the query
     */
    public QueryUI(QueryBuilder queryBuilder, QuerySubmitter querySubmitter) {
        super("String manager: Query");
        this.queryBuilder = queryBuilder;
        this.querySubmitter = querySubmitter;
        this.rowsModel = new DefaultListModel<>();

        initializeUI();
    }

    private void initializeUI() {
        setContentPane(rootPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        queryButton.addActionListener(actionEvent -> querySubmitter.submit(queryBuilder.create()));
        clearButton.addActionListener(actionEvent -> {
            queryBuilder.clear();
            rowsModel.clear();
        });
        newRowInsertText.addActionListener(actionEvent -> {
            String text = newRowInsertText.getText();
            addRow(text);
            newRowInsertText.setText("");
        });
        rowsList.setModel(rowsModel);
        rowsList.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE
                        || e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    removeSelected();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        rowsList.addListSelectionListener(e -> updateSelection());
    }

    private void updateSelection() {
        if (rowsList.getSelectedIndex() == -1) {
            rowsList.setSelectedIndex(0);
        }
    }

    private void removeSelected() {
        int[] selection = rowsList.getSelectedIndices();
        for (int i = selection.length - 1; i >= 0; i--) {
            rowsModel.removeElementAt(selection[i]);
        }
        updateSelection();
    }

    private void addRow(String newRow) {
        queryBuilder.add(newRow);
        rowsModel.addElement(newRow);
        updateSelection();
    }

    /**
     * Show the window
     */
    public void start() {
        setVisible(true);
    }
}
