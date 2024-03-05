package ba.smoki.six;

import ba.smoki.five.table.ColorCellEditor;
import ba.smoki.five.table.ColorCellRenderer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class PlayerPanel extends JPanel {
    private final JTable playerTable;
    private final TableRowSorter<PlayerTableModel> tableRowSorter;
    private final JTextField filterTextField;

    public PlayerPanel(){
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

        add(new JScrollPane(playerTable));
        add(formPanel);
    }

    private void setupSportColumnModel(){
        TableColumn sportColumn = playerTable.getColumnModel().getColumn(3);
        JComboBox<String> sportComboBox = new JComboBox<>();
        sportComboBox.addItem("Košarka");
        sportComboBox.addItem("Balet");
        sportComboBox.addItem("Fudbal");
        sportComboBox.addItem("Tenis");
        sportComboBox.addItem("Odbojka");
        sportColumn.setCellEditor(new DefaultCellEditor(sportComboBox));
    }



    private class FilterRowDocumentListener implements DocumentListener{

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

        private void filter(){
            //LIKE %ić%ž
            //Ovdje vežemo ono što je korisnik unio u filterTextField sa rowFilter
            RowFilter<PlayerTableModel, Object> rowFilter = RowFilter.regexFilter(filterTextField.getText(), 0);
            //Taj rowFilter koji priča sa filterTextField smo dodijelili tabeli preko tableRowSorter
            tableRowSorter.setRowFilter(rowFilter);

        }
    }


    private class PlayerTableDataChangeListener implements TableModelListener{
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


    private class PlayerTableModel extends AbstractTableModel{


        private final PlayerDao playerDao = new PlayerDao();
        private final List<String> playerColumnNames;
        private final List<Player> players;
        public PlayerTableModel(){
            this.players = playerDao.findAll();
            this.playerColumnNames = playerDao.findColumnNames();
            this.playerColumnNames.remove(0);
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
            Player player =  players.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> player.getName();
                case 1 -> player.getSurname();
                case 2 -> player.getColor();
                case 3 -> player.getSport();
                case 4 -> player.getYears();
                case 5 -> player.getVegetarian();
                default -> "";
            };
        }


        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Object cellValue = getValueAt(0, columnIndex);
            return cellValue !=null ? cellValue.getClass(): String.class;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Player player = players.get(rowIndex);
            switch (columnIndex) {
                case 0 -> player.setName((String) aValue);
                case 1 -> player.setSurname((String) aValue);
                case 2 -> player.setColor((Color) aValue);
                case 3 -> player.setSport((String) aValue);
                case 4 -> player.setYears((Integer) aValue);
                case 5 -> player.setVegetarian((Boolean) aValue);
            }
            playerDao.update(player);
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }
}
