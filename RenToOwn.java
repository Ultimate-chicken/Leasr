import java.util.ArrayList;

/**
 * Write a description of class RentToOwn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RentToOwn extends RecurringContract
{
    public RentToOwn(String contractID, ArrayList<Product> productSelection, Account linkedAccount)
    {
        super(contractID, productSelection, linkedAccount);
    }
}
