package ba.smoki.four.demo.pattern;

import java.math.BigDecimal;

public class CalculatorExecutor {
    private Calculator calculator;

    public BigDecimal executeCalculation(BigDecimal n1 , BigDecimal n2){
        return calculator.calculate(n1, n2);
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }
}
