import java.util.ArrayList;

/**
 * Write a description of class Lease here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lease extends RecurringContract {

    private double monthlyLeaseCost;
    private double depositAmount;
    private double leasePercentage;  
    private double depositPercentage;

    public Lease(String contractID, ArrayList<String> productSelection, String linkedAccount, double totalCartCost, String productDetails, double monthlyPayment, int totalPaymentPeriods, double depositAmount) {
        super(contractID, productSelection, linkedAccount, totalCartCost, productDetails, totalPaymentPeriods);
        this.leasePercentage = 0.7;
        this.depositPercentage = 0.1;

        calculateLeaseDetails()
    }

    private void calculateLeaseDetails() {
        double basePrice = getTotalCost();
        double initialMonthlyPayment = basePrice * leasePercentage;
        this.depositAmount = initialMonthlyPayment * depositPercentage;
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
