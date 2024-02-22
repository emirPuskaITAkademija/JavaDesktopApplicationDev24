package ba.smoki.four.demo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class ComboBoxListener implements ActionListener {
    private Consumer<String> stringValueConsumer;
    public ComboBoxListener(Consumer<String> stringValueConsumer) {
        this.stringValueConsumer = stringValueConsumer;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
        String selectedValue = (String) comboBox.getSelectedItem();
        stringValueConsumer.accept(selectedValue);
    }
}
