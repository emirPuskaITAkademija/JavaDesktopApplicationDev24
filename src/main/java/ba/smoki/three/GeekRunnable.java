package ba.smoki.three;

import javax.swing.*;
import java.awt.*;

public class GeekRunnable implements Runnable{
    @Override
    public void run() {
        JFrame prozor = new JFrame("Zdravo korisniče");
        prozor.setMinimumSize(new Dimension(200, 200));

        JPanel geekPanel = new GeekPanel();
        prozor.setContentPane(geekPanel);

        prozor.pack();
        prozor.setVisible(true);
    }
}
