import java.util.ArrayList;

/**
 * Write a description of class Lease here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lease extends RecurringContract {
    public Lease(String contractID, ArrayList<Product> productSelection, Account linkedAccount, 
    int leaseDuration) {
        super(contractID, productSelection, linkedAccount, leaseDuration);

        double basePrice = getTotalCost();
        this.monthlyPayment = basePrice * 0.7;

        this.depositAmount = this.monthlyPayment * 0.1;

        this.monthlyPayment = ((this.monthlyPayment - this.depositAmount) / leaseDuration);
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

