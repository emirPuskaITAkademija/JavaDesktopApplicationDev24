package ba.smoki.four.demo.pattern;

import java.math.BigDecimal;

public class Tester {
    public static void main(String[] args) {
        Calculator calculator = new DivideCalculator();
        CalculatorExecutor calculatorExecutor = new CalculatorExecutor();
        calculatorExecutor.setCalculator(calculator);
        calculatorExecutor.setCalculator(new MultiplyCalculator());
        BigDecimal value = calculatorExecutor.executeCalculation(BigDecimal.valueOf(20), BigDecimal.ZERO);
        System.out.println(value);
    }
}
