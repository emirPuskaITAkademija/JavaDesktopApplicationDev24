package ba.smoki.two;

import javax.swing.*;
import java.awt.*;

/**
 * <li>1. JFrame, JPanel</li>
 * <li>2. JButton</li>
 * <li>3. BorderLayout, FlowLayout</li>
 */
public class ButtonDemonstration {
    public static void main(String[] args) {
        Runnable runnable =  ButtonDemonstration::createGUI;
//        new Thread(runnable).start();
        SwingUtilities.invokeLater(runnable);
    }

    private static void createGUI(){
        System.out.println("GUI crta: " + Thread.currentThread().getName());
        JFrame frame = new JFrame("Prozor sa živim dugmićima");
        JPanel panel = new ButtonPanel();
        frame.add(panel, BorderLayout.CENTER);
        JButton programExitButton = new JButton("EXIT");
        programExitButton.addActionListener(e->{
            System.exit(0);
        });
        frame.add(programExitButton, BorderLayout.PAGE_END);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
