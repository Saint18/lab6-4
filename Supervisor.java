
//package organization;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;


/**
 * This class represents an employee in a supervisory role. This means that this
 * employee supervises at least one other employee
 */
public class Supervisor extends GenericEmployee {

  private List<Employee> superviseeList;

  //contructor as linked list ready to store supervisees
  public Supervisor(String name, double pay, Gender gender) {
    super(name, pay, gender);
    superviseeList = new LinkedList<Employee>();
  }


  //attempts to add supervisee to supervisor if supervisorName matches supervisor's name
  @Override
  public Employee addSupervisee(String supervisorName, Employee supervisee) {
    if (this.name.equals(supervisorName)) {
      this.superviseeList.add(supervisee);
      return this;
    }
    for (int i = 0; i < this.superviseeList.size(); i++) {
      this.superviseeList.set(
              i,
              this.superviseeList.get(i).addSupervisee(supervisorName,
                      supervisee));
    }
    return this;
  }
  
  
  //attempts to remove supervisor if supervisorName matches supervisor's name. iterates through list
  @Override
  public Employee removeSupervisee(String supervisorName, Employee supervisee) {
    if (this.name.equals(supervisorName)) {
      this.superviseeList.remove(supervisee);
      return this;
    }
    for (int i = 0; i < this.superviseeList.size(); i++) {
      this.superviseeList.set(
              i,
              this.superviseeList.get(i).removeSupervisee(supervisorName,
                      supervisee));
    }
    return this;
  }
  

  //count employees under supervisor that meets specific condition
  @Override
  public int count(Predicate<Employee> condition) {
    Stream<Employee> stream = this.superviseeList.stream();
    return this.superviseeList.stream()
                   .mapToInt(b -> b.count(condition))
                   .sum()
           + super.count(condition);
  }

  
  //returns a list of all employees under this supervisor including supervisor
  @Override
  public List<Employee> toList() {
    List<Employee> result = new ArrayList<Employee>();
    result.add(this);
    for (Employee e : superviseeList) {
      result.addAll(e.toList());
    }
    return result;
  }
  
  
  //filers employees based on a given predicate
  @Override
  public List<Employee> toList(Predicate<Employee> predicate) {
    List<Employee> result = new ArrayList<Employee>();
    
	if(predicate.test(this))result.add(this);
	
    for (Employee e : superviseeList) {
      result.addAll(e.toList(predicate));
    }
    return result;
  }
  
  
  //print supervisor and recursively print all supervisees 
  public void printEmployees()
  {
	  System.out.println("SUPERVISOR " + this);
	  
	  for (Employee e : superviseeList)
	  {
		  e.printEmployees();
	  }
  }
  
  //getter
  public List<Employee> getSuperviseeList(){
	  return this.superviseeList;
  }
}
