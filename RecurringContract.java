import java.util.ArrayList;

/**
 * Write a description of class RecurringContract here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class RecurringContract extends Contract
{
    /**
     * Constructor for objects of class RecurringContract
     */
    public RecurringContract(String contractID, ArrayList<Product> productSelection, Account linkedAccount)
    {
        super(contractID, productSelection, linkedAccount);
    }

}
