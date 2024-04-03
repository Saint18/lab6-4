
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * This class represents the data and operations of any employee. It defines all
 * the operations that either ought to be common to all implementations, or have
 * a reasonable default to be overridden by them.
 */

public abstract class GenericEmployee implements Employee {
	protected String name;
	protected double pay;
	protected Gender gender;

	// constructor initializing name, pay, gender
	public GenericEmployee(String name, double pay, Gender gender) {
		this.name = name;
		this.pay = pay;
		this.gender = gender;
	}

	// implements get name returning employee name.
	@Override
	public String getName() {
		return this.name;
	}

	// implements get gender returning employee gender
	@Override
	public Gender getGender() {
		return this.gender;
	}

	// implements get annual pay returning employee annual pay.
	@Override
	public double getAnnualPay() {
		return this.pay;
	}

	// returns placeholder. only contract employees have end date
	@Override
	public String getEmploymentEndDate() {
		return "XXXXXXXX";
	}

	// implements count method to evaluale if employee meets specific condition
	@Override
	public int count(Predicate<Employee> condition) {
		if (condition.test(this)) {
			return 1;
		}
		return 0;
	}

	// represents a single employee as a list
	@Override
	public List<Employee> toList() {
		List<Employee> result = new ArrayList<Employee>();
		result.add(this);
		return result;
	}

	// creates a list that will only include the current employee if they meet the
	// condition specified by the predicate.
	@Override
	public List<Employee> toList(Predicate<Employee> predicate) {
		List<Employee> result = new ArrayList<Employee>();

		if (predicate.test(this))
			result.add(this);

		return result;
	}

	// prints employee details
	public String toString() {
		return "Name : " + name + ", Gender : " + gender + ", Pay : " + pay;
	}

}
