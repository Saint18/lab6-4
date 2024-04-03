
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents an employee who works on a term-limited contract
 * with the organization. Contract employees cannot manage
 * other employees within this organization.
 */
public class ContractEmployee extends NonManagerEmployee {
  private LocalDate contractEndDate;

  //constructor calling super class NonManagerEmployee. 
  //throw exception if date is invalid. 
  public ContractEmployee(String name, double pay, Gender gender, int date, int
   month, int year) throws IllegalArgumentException{
    super(name, pay, gender);
    try {
      contractEndDate = LocalDate.of(year, month, date);
    }
    catch (DateTimeException e) {
      throw new IllegalArgumentException("Invalid contract end date");
    }
  }

  
  //return end date in a specific format
  @Override
  public String getEmploymentEndDate() {
    return DateTimeFormatter.ofPattern("MMddyyyy").format(contractEndDate);
  }
  
  
  //prints employee type contractor followed superclass's method to print employee details. 
  public void printEmployees()
  {
	  System.out.print("CONTRACTOR ");
	  super.printEmployees();
	  
  }
}
