package ru.ifmo.ctddev.elite.query;

import javax.swing.*;

/**
 * @author Zakhar Voit (zakharvoit@gmail.com)
 */
public class QueryUI extends JFrame {
    private JList<String> rowsList;
    private JPanel rootPanel;
    private JTextField newRowInsertText;
    private JButton insertNewRowButton;
    private QueryBuilder queryBuilder;
    private DefaultListModel<String> rowsModel;

    /**
     * Create new UI for building the query using queryBuilder.
     *
     * @param queryBuilder a builder to use for creating the query
     */
    public QueryUI(QueryBuilder queryBuilder) {
        super("String manager: Query");
        this.queryBuilder = queryBuilder;
        this.rowsModel = new DefaultListModel<>();

        initializeUI();
    }

    private void initializeUI() {
        setContentPane(rootPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        insertNewRowButton.addActionListener(actionEvent -> {
            String text = newRowInsertText.getText();
            addRow(text);
            newRowInsertText.setText("");
        });
        rowsList.setModel(rowsModel);
    }

    private void addRow(String newRow) {
        queryBuilder.add(newRow);
        rowsModel.addElement(newRow);
    }

    /**
     * Show the window
     */
    public void start() {
        setVisible(true);
    }
}
