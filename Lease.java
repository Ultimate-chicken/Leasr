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
    }

    /*@Override
    public String toString() {
        return "Lease " + super.toString() +
        "\nMonthly Lease Cost: " + monthlyLeaseCost + "€" +
        "\nLease Duration: " + leaseDuration + " months" +
        "\nDeposit Amount: " + depositAmount + "€";
    }*/
}

