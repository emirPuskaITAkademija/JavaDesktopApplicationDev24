package ba.smoki.five.table;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimpleTablePanel extends JPanel {
    private final Object[] columnNames = {"First Name", "Last Name", "Sport", "Years", "Vegetarian"};
    private Object[][] tableData = {
            {"Muhamed", "Salihović", "Košarka", 3, true},
            {"Mile", "Vukajlović", "Košarka", 13, false},
            {"Kanita", "Berbić", "Odbojka", 6, true},
            {"Nejra", "Kadrić", "Tenis", 3, true},
            {"Alen", "Botić", "Fubdal", 10, false},
            {"Kemal", "Selimotić", "Balet", 6, true},
            {"Muhamed", "Salihović", "Košarka", 3, true},
            {"Mile", "Vukajlović", "Košarka", 13, false},
            {"Kanita", "Berbić", "Odbojka", 6, true},
            {"Nejra", "Kadrić", "Tenis", 3, true},
            {"Alen", "Botić", "Fubdal", 10, false},
            {"Kemal", "Selimotić", "Balet", 6, true}
    };

    private final JTable table = new JTable(tableData, columnNames);

    public SimpleTablePanel() {
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        TableMouseAdapter tableMouseAdapter = new TableMouseAdapter();
        table.addMouseListener(tableMouseAdapter);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private class TableMouseAdapter extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            int rowNumber = table.getRowCount();
            int columnNumber = table.getColumnCount();
            TableModel tableModel = table.getModel();
            for(int i = 0; i<rowNumber; i++){
                for(int j = 0; j<columnNumber; j++){
                    System.out.print(" " + tableModel.getValueAt(i, j));
                }
                System.out.println();
            }
        }
    }
}
