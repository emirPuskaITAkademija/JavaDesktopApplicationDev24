package ba.smoki.four.demo.pattern;

import java.math.BigDecimal;

public class MultiplyCalculator implements Calculator{
    @Override
    public BigDecimal calculate(BigDecimal number1, BigDecimal number2) {
        return number1.multiply(number2);
    }
}
