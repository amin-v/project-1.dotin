package bankproject.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class QarzolHasane extends Deposit implements CalcInterest, Serializable {

    public QarzolHasane() {
    }

    public QarzolHasane(String customerNumber, String depositType, BigDecimal depositBalance, int durationInDays) {
        super(customerNumber, depositType, depositBalance, durationInDays);
    }

    @Override
    public BigDecimal payedInterest() {
        return BigDecimal.valueOf(0);
    }
}
