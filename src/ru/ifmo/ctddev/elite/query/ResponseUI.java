package ru.ifmo.ctddev.elite.query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * @author Zakhar Voit (zakharvoit@gmail.com)
 */
public class ResponseUI extends JFrame {
    private JPanel rootPanel;
    private JTable responsesTable;

    public ResponseUI(List<String> query, List<Integer> response)  {
        super("String manager: query response");

        initializeUI(query, response);
        setContentPane(rootPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
    }

    public void start() {
        setVisible(true);
    }

    private void initializeUI(List<String> query, List<Integer> response) {
        DefaultTableModel model = (DefaultTableModel) responsesTable.getModel();
        model.addColumn("String");
        model.addColumn("Count");
        for (int i = 0; i < query.size(); i++) {
            model.addRow(new Object[]{query.get(i), response.get(i)});
        }
    }
}
