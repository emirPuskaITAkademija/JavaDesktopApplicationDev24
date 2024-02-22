package ba.smoki.four.demo.pattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorActionListener implements ActionListener {
    private Calculator calculator;

    public CalculatorActionListener(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
