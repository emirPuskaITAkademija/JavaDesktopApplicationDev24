package ba.smoki.two.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AngelListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
        JButton button = (JButton)event.getSource();
        System.out.println("Ja sam anđeo pretplaćen na tebe dugmiću...: " + button.getText());
    }
}
