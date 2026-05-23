import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETask implements TaskInterface<BigDecimal> {
    private final int digits;

    public ETask(int digits) {
        this.digits = digits;
    }

    @Override
    public BigDecimal execute() {
        int scale = digits + 5;
        BigDecimal e = BigDecimal.ONE; // For n=0, 1/0! = 1
        BigDecimal term = BigDecimal.ONE;
        int n = 1;
        
        while (term.compareTo(BigDecimal.ZERO) != 0) {
            term = term.divide(BigDecimal.valueOf(n), scale, RoundingMode.HALF_UP);
            e = e.add(term);
            n++;
        }
        
        return e.setScale(digits, RoundingMode.HALF_UP);
    }
}