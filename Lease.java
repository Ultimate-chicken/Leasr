import java.util.ArrayList;

/**
 * Write a description of class Lease here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lease extends Contract {
    private double monthlyLeaseCost;
    private int leaseDuration;
    private double depositAmount;

    public Lease(String contractID, ArrayList<Product> productSelection, Account linkedAccount, 
    double monthlyLeaseCost, int leaseDuration, double depositAmount) {
        super(contractID, productSelection, linkedAccount);
        this.monthlyLeaseCost = monthlyLeaseCost;
        this.leaseDuration = leaseDuration;
        this.depositAmount = depositAmount;
    }

    @Override
    public String toString() {
        return "Lease " + super.toString() +
        "\nMonthly Lease Cost: " + monthlyLeaseCost + "€" +
        "\nLease Duration: " + leaseDuration + " months" +
        "\nDeposit Amount: " + depositAmount + "€";
    }
}

