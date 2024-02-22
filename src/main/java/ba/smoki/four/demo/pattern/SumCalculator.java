package ba.smoki.four.demo.pattern;

import java.math.BigDecimal;

public class SumCalculator implements Calculator{
    @Override
    public BigDecimal calculate(BigDecimal number1, BigDecimal number2) {
        BigDecimal suma = number1.add(number2);
        return suma;
    }
}
