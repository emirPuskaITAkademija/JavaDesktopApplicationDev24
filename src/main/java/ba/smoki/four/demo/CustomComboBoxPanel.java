package ba.smoki.four.demo;

import ba.smoki.three.image.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomComboBoxPanel extends JPanel {

    private IconLoader iconLoader = new IconLoader(CustomComboBoxPanel.class);
    String[] petStringArray = {"Bird", "Cat", "Pig", "Rabbit"};

    private JComboBox<PetModel> petModelJComboBox;

    public CustomComboBoxPanel() {
        setLayout(new BorderLayout());
        PetModel[] petModels = loadItems();
        petModelJComboBox = new JComboBox<>(petModels);
        ComboBoxRenderer comboBoxRenderer = new ComboBoxRenderer();
        petModelJComboBox.setRenderer(comboBoxRenderer);
        add(petModelJComboBox, BorderLayout.PAGE_START);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private PetModel[] loadItems() {
        List<PetModel> petModels = new ArrayList<>();
        for (String petName : petStringArray) {
            String prefix = "images/animal/";
            String sufix = ".gif";
            String imagePath = String.format("%s%s%s", prefix, petName, sufix);
            PetModel petModel = new PetModel();
            petModel.setPetName(petName);
            petModel.setImageIcon(iconLoader.loadIcon(imagePath));
            petModels.add(petModel);
        }
        return petModels.toArray(new PetModel[petModels.size()]);
    }


    private class ComboBoxRenderer implements ListCellRenderer<PetModel> {

        @Override
        public Component getListCellRendererComponent(JList<? extends PetModel> list, PetModel petModel, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = new JLabel();
            label.setIcon(petModel.getImageIcon());
            label.setText(petModel.getPetName());
            return label;
        }
    }
}
