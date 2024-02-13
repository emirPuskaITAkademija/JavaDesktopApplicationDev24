package ba.smoki.two;

import ba.smoki.three.image.IconLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

//ButtonPanel buttonPanel = new ButtonPanel();
// leftButton
// middleButton
// rightButton
public class ButtonPanel extends JPanel {
    public static final String DISABLE = "DISABLE";
    public static final String ENABLE = "ENABLE";
    private final JButton leftButton;
    private final JButton middleButton;
    private final JButton rightButton;

    private final IconLoader iconLoader = new IconLoader(ButtonPanel.class);

    public ButtonPanel() {

        ActionListener universalButtonListener = this::onButtonClick;

        Icon rightIcon = iconLoader.loadIcon("images/right.gif");
//                createIcon("images/right.gif");
        leftButton = new JButton("Disable middle button", rightIcon);
        leftButton.setVerticalTextPosition(SwingConstants.CENTER);
        leftButton.setHorizontalTextPosition(SwingConstants.LEADING);
        leftButton.setMnemonic(KeyEvent.VK_LEFT);
        leftButton.setActionCommand(DISABLE);
        leftButton.addActionListener(universalButtonListener);
        leftButton.setToolTipText("Ovo je lijevi disable dugmić..");


//        AngelListener angelListener = new AngelListener();
//        DevilListener devilListener = new DevilListener();
//        leftButton.addActionListener(angelListener);
//        leftButton.addActionListener(devilListener);

        Icon middleImageIcon = createIcon("images/middle.gif");
        middleButton = new JButton("Middle button", middleImageIcon);
        middleButton.setHorizontalTextPosition(SwingConstants.CENTER);
        middleButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        middleButton.setMnemonic(KeyEvent.VK_DOWN);
        middleButton.addActionListener(universalButtonListener);
        middleButton.setToolTipText("Ovo je VICTIM..");

        Icon leftIcon = createIcon("images/left.gif");
        rightButton = new JButton("Enable middle button", leftIcon);
        rightButton.setMnemonic(KeyEvent.VK_RIGHT);
        rightButton.addActionListener(universalButtonListener);
        rightButton.setActionCommand(ENABLE);
        rightButton.setToolTipText("Ovo je desni ENABLE dugmić...");


        add(leftButton);
        add(middleButton);
        add(rightButton);
    }

    private void onButtonClick(ActionEvent event){
        String actionCommand = event.getActionCommand();
        switch (actionCommand){
            case ENABLE -> {
                middleButton.setEnabled(true);
                rightButton.setEnabled(false);
                leftButton.setEnabled(true);
                System.out.println(ENABLE);
            }
            case DISABLE -> {
                middleButton.setEnabled(false);
                leftButton.setEnabled(false);
                rightButton.setEnabled(true);
                System.out.println(DISABLE);
            }
        }
    }

    private Icon createIcon(String pictureName) {
        IconLoader iconLoader = new IconLoader(ButtonPanel.class);
        Icon icon = iconLoader.loadIcon(pictureName);
        return icon;
//        ClassLoader classLoader = ButtonPanel.class.getClassLoader();
//        URL imageAddress = classLoader.getResource(pictureName);
//        if (imageAddress != null) {
//            ImageIcon imageIcon = new ImageIcon(imageAddress);
//            return imageIcon;
//        }
//        System.err.println("No image with name " + pictureName);
//        return null;
    }
}
