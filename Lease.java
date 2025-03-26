import java.util.ArrayList;

/**
 * Write a description of class Lease here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lease extends RecurringContract {
    public Lease(String contractID, ArrayList<String> productSelection, String linkedAccount, double totalCartCost, String productDetails, double monthlyPayment, int totalPaymentPeriods, double depositAmount) {
        super(contractID, productSelection, linkedAccount, totalCartCost, productDetails, totalPaymentPeriods);

        double basePrice = getTotalCost();
        this.monthlyPayment = basePrice * 0.7;

        this.depositAmount = this.monthlyPayment * 0.1;

        this.monthlyPayment = ((this.monthlyPayment - this.depositAmount) / totalPaymentPeriods);
    }

    /*@Override
    public String toString() {
    return "Lease " + super.toString() +
    "\nMonthly Lease Cost: " + monthlyLeaseCost + "€" +
    "\nLease Duration: " + leaseDuration + " months" +
    "\nDeposit Amount: " + depositAmount + "€";
    }*/
    @Override
    protected String getContractType() {
        return "Lease";
    }
}
