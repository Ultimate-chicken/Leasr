import java.util.ArrayList;

/**
 * Write a description of class RentToOwn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class RentToOwn extends Contract {
    private double monthlyPayment;
    private int totalPaymentPeriods;

    public RentToOwn(String contractID, ArrayList<Product> productSelection, Account linkedAccount, 
                     double monthlyPayment, int totalPaymentPeriods) {
        super(contractID, productSelection, linkedAccount);
        this.monthlyPayment = monthlyPayment;
        this.totalPaymentPeriods = totalPaymentPeriods;
    }

    @Override
    public String toString() {
        return "Rent-to-Own " + super.toString() +
               "\nMonthly Payment: " + monthlyPayment + "€" +
               "\nTotal Payment Periods: " + totalPaymentPeriods + " months";
    }
}
