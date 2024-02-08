package ba.smoki.two;

import javax.swing.*;
import java.awt.*;

/**
 * GUI
 * <li>1. Kontejner - Container
 *   <li>1.1 JFrame</li>
 *   <li>1.2 JPanel</li>
 * </li>
 * <li>2. GUI Kontrole
 *   <li>2.1 JButton</li>
 * </li>
 * <li>3. Menadzeri - LayoutManager
 *   <li>3.1 BorderLayout (JFrame)</li>
 *   <li>3.2 FlowLayout(JPanel)</li>
 * </li>
 */
public class ButtonDemo {

    public static void main(String[] args) {
        //Prozor ili Windows
        JFrame frame = new JFrame("Button Demonstration");

        JButton topButton = new JButton("PAGE START");
        JButton lineStartButton = new JButton("LINE START");
        JButton centerButton = new JButton("CENTER");
        JButton lineEndButton = new JButton("LINE END");
        JButton bottomButton = new JButton("PAGE END");

        frame.add(topButton, BorderLayout.PAGE_START);
        frame.add(lineStartButton, BorderLayout.LINE_START);
        frame.add(centerButton, BorderLayout.CENTER);
        frame.add(lineEndButton, BorderLayout.LINE_END);
        frame.add(bottomButton, BorderLayout.PAGE_END);

        Dimension dimension = new Dimension();
        dimension.setSize(500, 400);
        frame.setMinimumSize(dimension);
        frame.setVisible(true);
    }
}
