package ba.smoki.six;

import ba.smoki.five.table.ColorCellEditor;
import ba.smoki.five.table.ColorCellRenderer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

public class PlayerPanel extends JPanel {
    public static final int SPORT_COLUMN_INDEX = 4;
    private final JTable playerTable;
    private final TableRowSorter<PlayerTableModel> tableRowSorter;
    private final JTextField filterTextField;

    public PlayerPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        PlayerTableModel playerTableModel = new PlayerTableModel();
        this.playerTable = new JTable(playerTableModel);
        this.tableRowSorter = new TableRowSorter<>(playerTableModel);
        this.playerTable.setRowSorter(this.tableRowSorter);
        PlayerTableDataChangeListener listener = new PlayerTableDataChangeListener();
        this.playerTable.getModel().addTableModelListener(listener);
        this.playerTable.setFillsViewportHeight(true);
        this.playerTable.setPreferredScrollableViewportSize(new Dimension(700, 100));
        this.playerTable.setDefaultEditor(Color.class, new ColorCellEditor());
        this.playerTable.setDefaultRenderer(Color.class, new ColorCellRenderer());
        setupSportColumnModel();
        JPanel formPanel = new JPanel(new GridLayout(1, 1));
        JLabel filterTextLabel = new JLabel("Filter:", SwingConstants.TRAILING);
        this.filterTextField = new JTextField();
        FilterRowDocumentListener documentListener = new FilterRowDocumentListener();
        this.filterTextField.getDocument().addDocumentListener(documentListener);
        filterTextLabel.setLabelFor(this.filterTextField);
        formPanel.add(filterTextLabel);
        formPanel.add(filterTextField);
        setupActionColumnModel();
        add(new JScrollPane(playerTable));
        add(formPanel);
    }

    private void setupActionColumnModel() {
        //TableColumn actionColumn = playerTable.getColumnModel().getColumn(0);
        DeleteAction deleteAction = new DeleteAction();
        ButtonColumn buttonColumn = new ButtonColumn(playerTable, deleteAction, 0);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
    }

    private class DeleteAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = (JTable) e.getSource();
            int modelRow = Integer.valueOf(e.getActionCommand());
            ((DefaultTableModel) table.getModel()).removeRow(modelRow);
        }
    }

    private void setupSportColumnModel() {
        TableColumn sportColumn = playerTable.getColumnModel().getColumn(SPORT_COLUMN_INDEX);
        JComboBox<String> sportComboBox = new JComboBox<>();
        sportComboBox.addItem("Košarka");
        sportComboBox.addItem("Balet");
        sportComboBox.addItem("Fudbal");
        sportComboBox.addItem("Tenis");
        sportComboBox.addItem("Odbojka");
        sportColumn.setCellEditor(new DefaultCellEditor(sportComboBox));
    }


    private class FilterRowDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            filter();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            filter();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            filter();
        }

        private void filter() {
            //LIKE %ić%ž
            //Ovdje vežemo ono što je korisnik unio u filterTextField sa rowFilter
            RowFilter<PlayerTableModel, Object> rowFilter = RowFilter.regexFilter(filterTextField.getText(), 0);
            //Taj rowFilter koji priča sa filterTextField smo dodijelili tabeli preko tableRowSorter
            tableRowSorter.setRowFilter(rowFilter);

        }
    }


    private class PlayerTableDataChangeListener implements TableModelListener {
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


    private class PlayerTableModel extends AbstractTableModel {


        private final PlayerDao playerDao = new PlayerDao();
        private final List<String> playerColumnNames;
        private final List<Player> players;

        public PlayerTableModel() {
            this.players = playerDao.findAll();
            this.playerColumnNames = playerDao.findColumnNames();
        }

        @Override
        public int getRowCount() {
            return players.size();
        }

        @Override
        public int getColumnCount() {
            return playerColumnNames.size();
        }

        @Override
        public String getColumnName(int column) {
            return playerColumnNames.get(column);
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Player player = players.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> player.getId();
                case 1 -> player.getName();
                case 2 -> player.getSurname();
                case 3 -> player.getColor();
                case 4 -> player.getSport();
                case 5 -> player.getYears();
                case 6 -> player.getVegetarian();
                default -> "";
            };
        }


        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Object cellValue = getValueAt(0, columnIndex);
            return cellValue != null ? cellValue.getClass() : String.class;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Player player = players.get(rowIndex);
            switch (columnIndex) {
                case 0 -> player.setId(Long.parseLong((String) aValue));
                case 1 -> player.setName((String) aValue);
                case 2 -> player.setSurname((String) aValue);
                case 3 -> player.setColor((Color) aValue);
                case 4 -> player.setSport((String) aValue);
                case 5 -> player.setYears((Integer) aValue);
                case 6 -> player.setVegetarian((Boolean) aValue);
            }
            playerDao.update(player);
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }
}
