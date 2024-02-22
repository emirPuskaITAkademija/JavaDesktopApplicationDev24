package ba.smoki.four.demo;

import ba.smoki.three.image.IconLoader;

import javax.swing.*;
import java.awt.*;

public class AnimalComboBoxPanel extends JPanel {

    String[] petStringArray = {"Bird", "Cat", "Pig", "Rabbit"};

    private JComboBox<String> petComboBox = new JComboBox<>(petStringArray);
    private JLabel petPictureLabel = new JLabel();
    private ComboBoxListener petComboBoxListener = new ComboBoxListener(this::updatePicture);

    public AnimalComboBoxPanel() {
        setLayout(new BorderLayout());
        petComboBox.addActionListener(petComboBoxListener);
        add(petComboBox, BorderLayout.PAGE_START);

        petPictureLabel.setPreferredSize(new Dimension(180, 140));
        add(petPictureLabel, BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        updatePicture((String) petComboBox.getSelectedItem());
    }

    public void updatePicture(String selectedPet) {
        IconLoader iconLoader = new IconLoader(AnimalComboBoxPanel.class);
        String prefix = "images/animal/";
        String sufix = ".gif";
        String imagePath = String.format("%s%s%s", prefix, selectedPet, sufix);
        Icon imageIcon = iconLoader.loadIcon(imagePath);
        petPictureLabel.setIcon(imageIcon);
    }
}
