import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Write a description of class Lease here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lease extends RecurringContract
{

    private double leaseMonthlyCost;
    private boolean leaseActive;

    public Lease(String contractID, ArrayList<Product> productSelection, Account linkedAccount)
    {
        super(contractID, productSelection, linkedAccount);
    }
}
