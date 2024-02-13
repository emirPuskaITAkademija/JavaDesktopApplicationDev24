package ba.smoki.three;

import javax.swing.*;

public class GeekFrame {
    public static void main(String[] args) {
//        Runnable runnable = GeekFrame::createAndShowGUI;
        Runnable runnable = new GeekRunnable();
        SwingUtilities.invokeLater(runnable);
        //new Thread(runnable).start()
    }

  /*  private static void createAndShowGUI() {
        JFrame prozor = new JFrame("Štreber");

        JPanel geekPanel = new GeekPanel();
        geekPanel.setOpaque(true);
        prozor.setContentPane(geekPanel);

        prozor.pack();
        prozor.setVisible(true);
    }

    private static class GeekRunnable implements Runnable{
        @Override
        public void run() {
            JFrame prozor = new JFrame("Štreber");

            JPanel geekPanel = new GeekPanel();
            geekPanel.setOpaque(true);
            prozor.setContentPane(geekPanel);

            prozor.pack();
            prozor.setVisible(true);
        }
    }*/
}
