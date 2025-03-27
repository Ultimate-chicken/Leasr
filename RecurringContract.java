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
    protected int remainingMonths;
    protected double remainingTotalCost;
    
    public RecurringContract(String contractID, ArrayList<String> productSelection, String linkedAccount, double totalCartCost, String productDetails, int contractLengthMonths) {
        super(contractID, productSelection, linkedAccount, totalCartCost, productDetails);
        this.contractLengthMonths = contractLengthMonths;
        this.remainingMonths = contractLengthMonths;
        this.remainingTotalCost = totalCartCost;
    }

    
    public double getRemainingAmount(int monthsPaid) {
        if (monthsPaid < 0 || monthsPaid > contractLengthMonths) {
            //add some comment or smthn
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
    
    /*public double getDepositAmount() {
        return depositAmount;
    }*/

    public void advanceContractByMonth() {
        if (remainingMonths > 0) {
            remainingMonths--;

            remainingTotalCost -= monthlyPayment;

            if (remainingMonths == 0) {
                //isActive = false;
            }
        }
    }

    public int getRemainingMonths() {
        return remainingMonths;
    }

    // Getter for remaining total cost
    public double getRemainingTotalCost() {
        return remainingTotalCost;
    }
}
