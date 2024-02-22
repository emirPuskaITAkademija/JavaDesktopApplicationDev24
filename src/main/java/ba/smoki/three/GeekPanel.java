package ba.smoki.three;

import ba.smoki.three.image.IconLoader;

import javax.swing.*;
import java.awt.*;

public class GeekPanel extends JPanel {

    private final JLabel greetingLabel = new JLabel("Zdravo korisniče");
    private final CheckBoxPanel checkBoxPanel;
    private final JLabel imageLabel;
    private StringBuffer imageName = new StringBuffer("cght");
    private final IconLoader iconLoader = new IconLoader(GeekPanel.class);


    public GeekPanel() {
        setLayout(new BorderLayout());
        checkBoxPanel = new CheckBoxPanel(this::updateImageLabel);
        imageLabel = new JLabel();
        updateImageLabel(new ChangeModel());
        add(greetingLabel, BorderLayout.PAGE_START);
        add(checkBoxPanel, BorderLayout.LINE_START);
        add(imageLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    }

    public void updateImageLabel(ChangeModel changeModel) {
        imageName.setCharAt(changeModel.getIndex(), changeModel.getSlovo());
        String prefix = "images/geek/geek-";
        String name = imageName.toString();
        String sufix = ".gif";
        String imageName = String.format("%s%s%s", prefix, name, sufix);
        Icon icon = iconLoader.loadIcon(imageName);
        imageLabel.setIcon(icon);
    }
}
