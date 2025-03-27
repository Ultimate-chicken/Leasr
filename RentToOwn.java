import java.util.ArrayList;

/**
 * Write a description of class RentToOwn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class RentToOwn extends RecurringContract {

    private static final double DEFAULT_INTEREST_RATE = 0.05;
    private static final double DEPOSIT_PERCENTAGE = 0.2; 

    private double interestRate;
    private double depositAmount;
    private double monthlyPayment;

    public RentToOwn(String contractID, ArrayList<String> productSelection, String linkedAccount, double totalCartCost, String productDetails, double monthlyPayment, int totalPaymentPeriods, double depositAmount) {
        super(contractID, productSelection, linkedAccount, totalCartCost, productDetails, totalPaymentPeriods);
        this.interestRate = DEFAULT_INTEREST_RATE;
        calculateTotalCost();
    }

    @Override
    protected void calculateTotalCost() {
        double basePrice = getTotalCost();
        this.depositAmount = basePrice * DEPOSIT_PERCENTAGE;
        double remainingBalance = basePrice - depositAmount;
        double finalCost = remainingBalance * (1 + DEFAULT_INTEREST_RATE);
        this.monthlyPayment = finalCost / getTotalPaymentPeriods();
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
