package bankproject.model;


import java.math.BigDecimal;
import java.math.RoundingMode;

import static bankproject.model.ShortTerm.INTEREST_RATE;
import static bankproject.model.ShortTerm.TOTAL_DAYS_COUNT;


public class Deposit implements CalcInterest, Comparable<Deposit> {
    //ctrl+alt+l
    public String customerNumber;
    public String depositType;
    public BigDecimal depositBalance;
    public int durationInDays;
    private CalcInterest calcInterest;

    public CalcInterest getCalcInterest() {
        return calcInterest;
    }

    public void setCalcInterest(CalcInterest calcInterest) {
        this.calcInterest = calcInterest;
    }

    public Deposit() {
    }

    public Deposit(String customerNumber, String depositType, BigDecimal depositBalance, int durationInDays) {
        this.customerNumber = customerNumber;
        this.depositType = depositType;
        this.depositBalance = depositBalance;
        this.durationInDays = durationInDays;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "customerNumber=" + customerNumber +
                ", depositType='" + depositType + '\'' +
                ", depositBalance=" + depositBalance +
                ", durationInDays=" + durationInDays +
                '}';
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(BigDecimal depositBalance) {
        this.depositBalance = depositBalance;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }


    @Override
    public BigDecimal payedInterest() {
        return depositBalance.multiply(BigDecimal.valueOf(durationInDays)).multiply(BigDecimal.valueOf(INTEREST_RATE)).divide(new BigDecimal(TOTAL_DAYS_COUNT), RoundingMode.HALF_UP);
    }

    @Override
    public int compareTo(Deposit o) {
        return this.payedInterest().compareTo(o.payedInterest());
    }
}
