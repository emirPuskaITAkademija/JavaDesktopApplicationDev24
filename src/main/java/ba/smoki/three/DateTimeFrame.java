package ba.smoki.three;

import javax.swing.*;
import java.awt.*;

public class DateTimeFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DateTimeFrame::createAndShowGUI);
    }

    private static void createAndShowGUI(){
        JFrame frame = new JFrame("Formatiranje datuma");

        DateTimePanel dateTimePanel = new DateTimePanel();
        frame.setContentPane(dateTimePanel);

        frame.pack();
        frame.setVisible(true);
    }
}
