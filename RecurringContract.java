import java.util.ArrayList;

/**
 * Write a description of class RecurringContract here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public abstract class RecurringContract extends Contract {
    protected double monthlyPayment;
    protected int contractLengthMonths;
    protected double depositAmount;
    
    public RecurringContract(String contractID, ArrayList<String> productSelection, String linkedAccount, double totalCartCost, String productDetails, int contractLengtMonths) {
        super(contractID, productSelection, linkedAccount, totalCartCost, productDetails);
        
        this.contractLengthMonths = contractLengthMonths;
    }
    
    public double getRemainingAmount(int monthsPaid) {
        if (monthsPaid < 0 || monthsPaid > contractLengthMonths) {
            throw new IllegalArgumentException("Invalid number of months paid");
        }
        
        double remainingPayments = contractLengthMonths - monthsPaid;
        return remainingPayments * monthlyPayment;
    }
    
    public double getMonthlyPayment() {
        return monthlyPayment;
    }
    
    public int getTotalPaymentPeriods() {
        return contractLengthMonths;
    }
    
    public double getDepositAmount() {
        return depositAmount;
    }
}
