package ba.smoki.three;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimePanel extends JPanel {
    String[] dateTimePatterns = {
            "dd MM yyyy",
            "dd.MM.yy",
            "MM/dd/yy",
            "H:mm:ss:SSS"
    };

    private JLabel labela1  = new JLabel("Unesite šablon za prikaz teksta ili ");
    private JLabel labela2 = new JLabel("odaberite jedan od ponuđenih šablona");
    private JLabel resultLabel = new JLabel("Prikaz Datuma/Vremena");
    private JLabel resultValueLabel = new JLabel();

    public DateTimePanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JComboBox<String> dateTimePatternComboBox = new JComboBox<>(dateTimePatterns);
        dateTimePatternComboBox.setEditable(true);
        DatePatternListener listener = new DatePatternListener();
        dateTimePatternComboBox.addActionListener(listener);
        JPanel patternPanel = new JPanel();
        patternPanel.setLayout(new BoxLayout(patternPanel, BoxLayout.Y_AXIS));
        patternPanel.add(labela1);
        patternPanel.add(labela2);
        dateTimePatternComboBox.setAlignmentX(LEFT_ALIGNMENT);
        Border outsideBorder = BorderFactory.createLineBorder(Color.BLACK);
        Border insideBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border compoundBorder = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);
        dateTimePatternComboBox.setBorder(compoundBorder);
        patternPanel.add(dateTimePatternComboBox);
        patternPanel.setAlignmentX(LEFT_ALIGNMENT);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(0, 1));
        resultPanel.add(resultLabel);
        resultValueLabel.setBorder(compoundBorder);
        resultPanel.add(resultValueLabel);
        resultPanel.setAlignmentX(LEFT_ALIGNMENT);

        add(patternPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(resultPanel);

        setBorder(BorderFactory.createEmptyBorder(20, 20 , 20, 20));
    }

    /**
     *
     * @param pattern
     */
    private void formatDate(String pattern){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String preformatiraniDatum = simpleDateFormat.format(date);
        resultValueLabel.setText(preformatiraniDatum);
    }

    private class DatePatternListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
            String newPattern = (String)comboBox.getSelectedItem();
            formatDate(newPattern);
        }
    }
}
