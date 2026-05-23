import java.math.BigDecimal;
import java.math.RoundingMode;

public class Task implements TaskInterface<BigDecimal> {
    private final int digits;

    public Task(int digits) {
        this.digits = digits;
    }

    @Override
    public BigDecimal execute() {
        int scale = digits + 5; // Additional precision for intermediate calculations

        // 16 * arctan(1/5)
        BigDecimal term1 = arctan(BigDecimal.valueOf(5), scale).multiply(BigDecimal.valueOf(16));
        // 4 * arctan(1/239)
        BigDecimal term2 = arctan(BigDecimal.valueOf(239), scale).multiply(BigDecimal.valueOf(4));
        
        return term1.subtract(term2).setScale(digits, RoundingMode.HALF_UP);
    }

    // Обчислення arctan(1/x) за допомогою ряду Тейлора, як на фото
    private BigDecimal arctan(BigDecimal inverseX, int scale) {
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal term;
        BigDecimal inverseX2 = inverseX.multiply(inverseX);
        BigDecimal numerator = BigDecimal.ONE.divide(inverseX, scale, RoundingMode.HALF_UP);
        int n = 0;
        
        do {
            int divisor = 2 * n + 1;
            term = numerator.divide(BigDecimal.valueOf(divisor), scale, RoundingMode.HALF_UP);
            
            if (n % 2 != 0) {
                result = result.subtract(term); // Віднімаємо (непарні члени)
            } else {
                result = result.add(term);      // Додаємо (парні члени)
            }
            
            numerator = numerator.divide(inverseX2, scale, RoundingMode.HALF_UP);
            n++;
        } while (term.compareTo(BigDecimal.ZERO) != 0); // Рахуємо, поки члени ряду впливають на результат
        
        return result;
    }
}