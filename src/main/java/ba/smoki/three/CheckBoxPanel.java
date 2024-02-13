package ba.smoki.three;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class CheckBoxPanel extends JPanel {
    private final JCheckBox chinCheckBox;
    private final JCheckBox glassesCheckBox;
    private final JCheckBox hairCheckBox;
    private final JCheckBox teethCheckBox;
    private final CheckBoxListener checkBoxListener = new CheckBoxListener();
    private final Consumer<ChangeModel> changeModelConsumer;

    public CheckBoxPanel(Consumer<ChangeModel> changeModelConsumer){
        this.changeModelConsumer = changeModelConsumer;
        setLayout(new GridLayout(0, 1));
        chinCheckBox = createCheckBox("Chin", true, KeyEvent.VK_C);
        glassesCheckBox = createCheckBox("Glasses", true, KeyEvent.VK_G);
        hairCheckBox = createCheckBox("Hair", true, KeyEvent.VK_H);
        teethCheckBox = createCheckBox("Teeth", true, KeyEvent.VK_T);
        add(chinCheckBox);
        add(glassesCheckBox);
        add(hairCheckBox);
        add(teethCheckBox);
    }

    private JCheckBox createCheckBox(String text, boolean selected, int mnemonic){
        JCheckBox checkBox = new JCheckBox(text, selected);
        checkBox.addItemListener(checkBoxListener);
        checkBox.setMnemonic(mnemonic);
        return checkBox;
    }

    // JCheckBox <---------- event ------------->CheckBoxListener
    private class CheckBoxListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            JCheckBox checkbox = (JCheckBox) event.getItemSelectable();
            // -  c g h t
            char slovo = '-';
            int index = 0;
            if(checkbox == chinCheckBox){
                index = 0;
                slovo = 'c';
            }else if(checkbox == glassesCheckBox){
                index = 1;
                slovo = 'g';
            }else if(checkbox == hairCheckBox){
                index = 2;
                slovo = 'h';
            }else if(checkbox == teethCheckBox){
                index = 3;
                slovo = 't';
            }
            if(event.getStateChange() == ItemEvent.DESELECTED){
                slovo = '-';
            }
            changeModelConsumer.accept(new ChangeModel(index, slovo));
        }
    }
}
