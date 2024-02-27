package ba.smoki.five.table;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ColorCellRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Color colorValue = (Color) value;
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(colorValue);
        return label;
    }
}
