package ba.smoki.six;

import javax.swing.*;

public class TableDemo {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(TableDemo::createAndShowGUI);
    }

    static void createAndShowGUI(){
        PlayerPanel playerPanel = new PlayerPanel();
        JFrame frame = new JFrame("Player Panel");
        frame.setContentPane(playerPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
