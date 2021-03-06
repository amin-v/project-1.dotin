package bankproject.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class LongTerm extends Deposit implements CalcInterest, Serializable {
    final static int INTEREST_RATE = 20;
    final static long TOTAL_DAYS_COUNT = 36500;

    public LongTerm() {
    }

    public LongTerm(String customerNumber, String depositType, BigDecimal depositBalance, int durationInDays) {
        super(customerNumber, depositType, depositBalance, durationInDays);
    }

    @Override
    public BigDecimal payedInterest() {
        return depositBalance.multiply(BigDecimal.valueOf(durationInDays)).multiply(BigDecimal.valueOf(INTEREST_RATE)).divide(new BigDecimal(TOTAL_DAYS_COUNT), RoundingMode.HALF_UP);
    }
}
