package ba.smoki.five;

import ba.smoki.five.table.SimpleTablePanel;
import ba.smoki.five.table.TablePanel;

import javax.swing.*;

public class TableDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TableDemo::createAndShowGUI);
    }

    static void createAndShowGUI(){
        JFrame frame = new JFrame("Table Demonstration");
       // SimpleTablePanel simpleTablePanel = new SimpleTablePanel();
        frame.setContentPane(new TablePanel());
        frame.pack();
        frame.setVisible(true);
    }
}
