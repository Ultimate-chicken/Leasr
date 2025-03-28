import java.util.Scanner;

/**
 * Write a description of class customerView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class viewClass
{
    Scanner userInput = new Scanner(System.in);
    
    public viewClass()
    {
        
    }
    
    //user input with validation. Only input. 
    public int getUserChoice() {
        int userInputNumber = Integer.parseInt(userInput.nextLine().trim());
            try {
            return userInputNumber;
        } catch (Exception e) {
            return -1; 
        }
    }
    
    //for searching and finding. gets input and output. 
    public void getOutputError(String ID) {
        System.out.printf("\nID %s was not found in our database.", ID);
    }
    
    public String getUserString(String detailType) {
        switch (detailType.trim()) {
            
        }
    }
    
    //when only output needs to be shown. 
    public void showSimpleMessage(String simpleMessage) {
        System.out.print(simpleMessage);
    }
    
}
