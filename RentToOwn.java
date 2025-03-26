import java.util.ArrayList;

/**
 * Write a description of class RentToOwn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class RentToOwn extends RecurringContract {

    public RentToOwn(String contractID, ArrayList<Product> productSelection, Account linkedAccount, int totalPaymentPeriods) {
        super(contractID, productSelection, linkedAccount, totalPaymentPeriods);

        double basePrice = getTotalCost();
        this.monthlyPayment = basePrice;

        this.depositAmount = this.monthlyPayment * 0.2;

        this.monthlyPayment = ((this.monthlyPayment  - this.depositAmount)/ totalPaymentPeriods);
    }

    /*@Override
    public String toString() {
    return "Rent-to-Own " + super.toString() +
    "\nMonthly Payment: " + String.format("%.2f", monthlyPayment) + "€" +
    "\nTotal Payment Periods: " + totalPaymentPeriods + " months" +
    "\nDeposit Amount: " + String.format("%.2f", depositAmount) + "€";
    }*/

    @Override
    protected String getContractType() {
        return "Rent-To-Own";
    }
}
