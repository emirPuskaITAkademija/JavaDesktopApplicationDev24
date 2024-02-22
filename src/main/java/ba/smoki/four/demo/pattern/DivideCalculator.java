package ba.smoki.four.demo.pattern;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivideCalculator implements Calculator{
    @Override
    public BigDecimal calculate(BigDecimal number1, BigDecimal number2) {
        if(number2.compareTo(BigDecimal.ZERO) == 0){
            throw new ArithmeticException("Division by zero is not possible");
        }
        return number1.divide(number2, 2, RoundingMode.HALF_UP);
    }
}
