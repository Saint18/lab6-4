import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class OrganizationImpl implements Organization {
	private GroupNode<Employee> boss; // the top-level node. top supervisor. ceo

	// constructor initializing ceo
	public OrganizationImpl(String name, double pay, Gender gender) {
		this.boss = new GroupNode<>(new Supervisor(name, pay, gender));
	}

	// creates new non manager employee and tries to add it under a supervisor with
	// specified name
	@Override
	public void addEmployee(String name, double pay, Gender gender, String supervisorName) {
		Employee newEmployee = new NonManagerEmployee(name, pay, gender);
		addEmployeeUnderSupervisor(boss, newEmployee, supervisorName);
	}

	// creats contract employee with end date and adds it under specified supervisor
	@Override
	public void addContractEmployee(String name, double pay, Gender gender, int endDate, int endMonth, int endYear,
			String supervisorName) {
		Employee newContractEmployee = new ContractEmployee(name, pay, gender, endDate, endMonth, endYear);
		addEmployeeUnderSupervisor(boss, newContractEmployee, supervisorName);
	}

	// helper method recursively searches for specified supervisor in hierarchy to
	// add the new employee as leaf node
	// if supervisor is found the employee is added as a child. if not it searches
	// children of current node.
	private void addEmployeeUnderSupervisor(GroupNode<Employee> supervisorNode, Employee employee,
			String supervisorName) {
		if (supervisorNode.getData().getName().equals(supervisorName)) {
			supervisorNode.addChild(new LeafNode<>(employee));
		} else {
			for (TreeNode<Employee> child : supervisorNode.getChildren()) {
				if (child instanceof GroupNode) {
					addEmployeeUnderSupervisor((GroupNode<Employee>) child, employee, supervisorName);
				}
			}
		}
	}

	// return number of employees in organization by converting tree to list
	@Override
	public int getSize() {
		return boss.toList().size();
	}

	// size by gender
	@Override
	public int getSizeByGender(Gender gender) {
		return (int) boss.toList().stream().filter(e -> e.getGender() == gender).count();
	}

	// returns list of all employees
	@Override
	public List<String> allEmployees() {
		List<String> names = new ArrayList<>();
		for (Employee e : boss.toList()) {
			names.add(e.getName());
		}
		return names;
	}

	// ability to count employees based on specific condition.
	public int getSize(Predicate<Employee> condition) {
		return (int) boss.toList().stream().filter(condition).count();
	}

}
