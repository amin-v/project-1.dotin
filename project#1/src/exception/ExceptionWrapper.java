package exception;

public class ExceptionWrapper {
    public static String getMessage(Exception e) {
        if (e instanceof DepositBalanceException) {
            return "Mande Mojodi Manfi Mibashad";
        } else if (e instanceof DepositTypeException) {
            return "noe seporde shoma dar list mojod nemibashad";
        } else if (e instanceof DurationDaysException) {
            return "teadad roozhaye moshtari sefr mibashad";
        } else {
            e.printStackTrace();
            return "connect to support unit 1005";
        }
    }
}
