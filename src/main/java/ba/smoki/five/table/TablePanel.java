package ba.smoki.five.table;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class TablePanel extends JPanel {

    private final JTable table;

    public TablePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        CustomTableModel customTableModel = new CustomTableModel();
        this.table = new JTable(customTableModel);
        this.table.setDefaultRenderer(Color.class, new ColorCellRenderer());
        this.table.setDefaultEditor(Color.class, new ColorCellEditor());
//        this.table.getSelectionModel().addListSelectionListener(new RowListener());
        DataChangeTableModelListener dataChangeTableModelListener = new DataChangeTableModelListener();
        this.table.getModel().addTableModelListener(dataChangeTableModelListener);
        this.table.setFillsViewportHeight(true);
        this.table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        add(new JScrollPane(table));
    }

    private class RowListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                System.out.println("Selektovan red = " + selectedRow);
            }
        }
    }

    private class DataChangeTableModelListener implements TableModelListener {

        @Override
        public void tableChanged(TableModelEvent tableModelEvent) {
            int rowIndex = tableModelEvent.getFirstRow();
            int columnIndex = tableModelEvent.getColumn();
            TableModel tableModel = (TableModel) tableModelEvent.getSource();
            String columnName = tableModel.getColumnName(columnIndex);
            Object data = tableModel.getValueAt(rowIndex, columnIndex);
            System.out.println("Desila se promjena u koloni s imenom: " + columnName);
            System.out.println("Novi podatak: " + data);
        }
    }

    private class CustomTableModel extends AbstractTableModel {
        private final String[] columnNames = {"First Name", "Last Name", "Favorite Color", "Sport", "Years", "Vegetarian"};
        private Object[][] tableData = {
                {"Muhamed", "Salihović", new Color(175, 175, 255), "Košarka", 3, true},
                {"Mile", "Vukajlović", new Color(155, 0, 155), "Košarka", 13, false},
                {"Kanita", "Berbić", new Color(250, 55, 100), "Odbojka", 6, true},
                {"Nejra", "Kadrić", new Color(120, 150, 200), "Tenis", 3, true},
                {"Alen", "Botić", new Color(155, 0, 155), "Fubdal", 10, false},
                {"Kemal", "Selimotić", new Color(155, 0, 155), "Balet", 6, true},
                {"Muhamed", "Salihović", new Color(155, 0, 155), "Košarka", 3, true},
                {"Mile", "Vukajlović", new Color(0, 155, 100), "Košarka", 13, false},
                {"Kanita", "Berbić", new Color(155, 0, 155), "Odbojka", 6, true},
                {"Nejra", "Kadrić", new Color(100, 50, 155), "Tenis", 3, true},
                {"Alen", "Botić", new Color(155, 0, 155), "Fubdal", 10, false},
                {"Kemal", "Selimotić", new Color(255, 0, 155), "Balet", 6, true}
        };

        @Override
        public int getRowCount() {
            return tableData.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Object cellValue = getValueAt(0, columnIndex);
//            if (cellValue != null) {
//                return cellValue.getClass();
//            } else {
//                return String.class;
//            }
            return cellValue !=null ? cellValue.getClass(): String.class;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return tableData[rowIndex][columnIndex];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (columnIndex < 2) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            tableData[rowIndex][columnIndex] = aValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }
}
