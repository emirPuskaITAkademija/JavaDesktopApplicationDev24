package ba.smoki.four.demo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeComboBoxPanel extends JPanel {
    static final String[] datePatterns = {
            "dd MMMMM yyyy",
            "dd.MM.yy",
            "yyyy.MM.dd G 'at' hh:mm:ss z",
            "h:mm a",
            "H:mm:ss:SSS",
            "yyyy.MMMMM.dd GGG hh:mm aaa"
    };

    private final JLabel patternLabel1 = new JLabel("Unesi pattern datuma ili");
    private final JLabel patternLabel2 = new JLabel("odaberi jedan datum iz liste ispod");
    private final JComboBox<String> dateTimePatternComboBox = new JComboBox<>(datePatterns);
    private final JLabel resultLabel = new JLabel("Trenutni datum/vrijeme", JLabel.LEADING);
    private final JLabel resultTextLabel = new JLabel();
    private String selectedPattern = datePatterns[0];
    private ComboBoxListener dateTimeComboBoxListener = new ComboBoxListener(this::reformatDateTime);

    public DateTimeComboBoxPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JPanel patternPanel = new JPanel();
        patternPanel.setLayout(new BoxLayout(patternPanel, BoxLayout.PAGE_AXIS));
        patternPanel.add(patternLabel1);
        patternPanel.add(patternLabel2);
        dateTimePatternComboBox.setAlignmentX(LEFT_ALIGNMENT);
        dateTimePatternComboBox.addActionListener(dateTimeComboBoxListener);
        dateTimePatternComboBox.setEditable(true);
        Border outsideBorder = BorderFactory.createLineBorder(Color.BLACK);
        Border insideBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border compoundBorder = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);
        dateTimePatternComboBox.setBorder(compoundBorder);
        patternPanel.add(dateTimePatternComboBox);
        patternPanel.setAlignmentX(LEFT_ALIGNMENT);

        JPanel resultPanel = new JPanel(new GridLayout(0, 1));
        resultPanel.setAlignmentX(LEFT_ALIGNMENT);
        resultPanel.add(resultLabel);
        resultPanel.add(resultTextLabel);
        resultTextLabel.setBorder(compoundBorder);

        add(patternPanel);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(resultPanel);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        reformatDateTime(selectedPattern);
    }


    private void reformatDateTime(String selectedPattern) {
        Date today = new Date();
        try{
            SimpleDateFormat formatter = new SimpleDateFormat(selectedPattern);
            String formattedDateTime = formatter.format(today);
            resultTextLabel.setForeground(Color.BLACK);
            resultTextLabel.setText(formattedDateTime);
        }catch (IllegalArgumentException exception){
            resultTextLabel.setForeground(Color.RED);
            resultTextLabel.setText("Error: " + exception.getMessage());
        }

    }

//    private class ComboBoxListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String selectedItemText = (String) dateTimePatternComboBox.getSelectedItem();
//            selectedPattern = selectedItemText;
//            reformatDateTime();
//        }
//    }
}
