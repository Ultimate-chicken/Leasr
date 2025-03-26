import java.util.ArrayList;

/**
 * Write a description of class RecurringContract here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public abstract class RecurringContract extends Contract {
    private double monthlyPayment;
    private int totalPaymentPeriods;
    private double depositAmount;
    
    public RecurringContract(String contractID, ArrayList<Product> productSelection, Account linkedAccount, int rentTermMonths) {
        super(contractID, productSelection, linkedAccount);
        
        double basePrice = getTotalCost();
        this.monthlyPayment = basePrice * 0.3;
        
        this.depositAmount = this.monthlyPayment * 0.2;
        
        this.monthlyPayment -= (this.depositAmount / rentTermMonths);
        
        this.totalPaymentPeriods = rentTermMonths;
    }
    
    public double getRemainingAmount(int monthsPaid) {
        if (monthsPaid < 0 || monthsPaid > totalPaymentPeriods) {
            throw new IllegalArgumentException("Invalid number of months paid");
        }
        
        double remainingPayments = totalPaymentPeriods - monthsPaid;
        return remainingPayments * monthlyPayment;
    }
    
    public double getMonthlyPayment() {
        return monthlyPayment;
    }
    
    public int getTotalPaymentPeriods() {
        return totalPaymentPeriods;
    }
    
    public double getDepositAmount() {
        return depositAmount;
    }
}