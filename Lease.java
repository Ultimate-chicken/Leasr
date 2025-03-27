import java.util.ArrayList;

/**
 * Write a description of class Lease here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lease extends RecurringContract {

    private static final double LEASE_PERCENTAGE = 0.7;
    private static final double LEASE_DEPOSIT_PERCENTAGE = 0.1;

    private double monthlyLeaseCost;
    private double depositAmount;
    private double leasePercentage;  
    private double depositPercentage;

    public Lease(String contractID, ArrayList<String> productSelection, String linkedAccount, double totalCartCost, String productDetails, double monthlyPayment, int totalPaymentPeriods, double depositAmount) {
        super(contractID, productSelection, linkedAccount, totalCartCost, productDetails, totalPaymentPeriods);
    }

    @Override
    protected void calculateTotalCost() {
        double basePrice = getTotalCost();
        double initialMonthlyPayment = basePrice * LEASE_PERCENTAGE;
        this.depositAmount = initialMonthlyPayment * LEASE_DEPOSIT_PERCENTAGE;
        this.monthlyLeaseCost = (initialMonthlyPayment - depositAmount) / getTotalPaymentPeriods();
    }

    @Override
    public String toString() {
        return "Lease " + super.toString() +
        "\nMonthly Lease Cost: " + monthlyLeaseCost + "€" +
            //"\nLease Duration: " + leaseDuration + " months" +
        "\nDeposit Amount: " + depositAmount + "€";
    }

    @Override
    protected String getContractType() {
        return "Lease";
    }
}
