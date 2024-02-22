package ba.smoki.four.demo;

import javax.swing.*;

public class ComboBoxDemo {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(ComboBoxDemo::createAndShowGUI);

    }

    private static void createAndShowGUI()  {

        JFrame frame = new JFrame("ComboBox Demonstration");
        JPanel panel1 = new DateTimeComboBoxPanel();
        AnimalComboBoxPanel panel2 = new AnimalComboBoxPanel();
        JPanel panel3 = new CustomComboBoxPanel();
        JPanel glavniPanel = new JPanel();
        glavniPanel.add(panel1);
        glavniPanel.add(panel2);
        glavniPanel.add(panel3);
        frame.setContentPane(glavniPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
