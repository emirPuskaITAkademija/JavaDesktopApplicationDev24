package ba.smoki.five.table;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ColorCellEditor extends AbstractCellEditor implements TableCellEditor {
    public static final String EDIT = "edit";
    private Color currentColor;
    private JButton button;
    private JColorChooser colorChooser;
    private JDialog dialog;

    public ColorCellEditor() {
        this.button = new JButton();
        this.button.setActionCommand(EDIT);
        this.button.addActionListener(this::showColorChooserDialog);

        this.colorChooser = new JColorChooser();
        this.dialog = JColorChooser.createDialog(button, //
                "Pick a color",
                true,
                colorChooser,
                this::onColorChoose,//okListener
                null);
    }


    public void onColorChoose(ActionEvent e) {
        currentColor = colorChooser.getColor();
    }

    private void showColorChooserDialog(ActionEvent e) {
        button.setBackground(currentColor);
        colorChooser.setColor(currentColor);
        dialog.setVisible(true);
        fireEditingStopped();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentColor = (Color) value;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return currentColor;
    }
}
