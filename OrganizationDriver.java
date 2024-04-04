public class OrganizationDriver {
    public static void main(String[] args) {
        // Initialize the organization with a CEO
        OrganizationImpl MonsterCorp = new OrganizationImpl("Ug the Terrible", 300000.00,Gender.UnDisclosed);
        
                MonsterCorp.addEmployee("Bob the undfeated", 300.00, Gender.Male, "Ug the Terrible");
                MonsterCorp.addEmployee("Gorg the smelly", 350.00, Gender.Female, "Ug the Terrible");
                MonsterCorp.addEmployee("Nix the ugly", 50.00, Gender.Male, "Ug the Terrible");
                MonsterCorp.addEmployee("Fredick the killer", 550.00, Gender.Male, "Ug the Terrible");
                MonsterCorp.addEmployee("Sue", 1000.00, Gender.Female, "Ug the Terrible");
                MonsterCorp.addEmployee("Hydra the sneaky", 350.00, Gender.Male, "Ug the Terrible");
                MonsterCorp.addEmployee("Gin the drunk", 300.00, Gender.Male, "Ug the Terrible");
                MonsterCorp.addEmployee("Lin the determined", 350.00, Gender.Female,"Ug the Terrible");
                MonsterCorp.addContractEmployee("Blarg the big", 300.00, Gender.Female,31,10,2020, "Ug the Terrible");

        System.out.println("Total number of employees in the organization: " + MonsterCorp.getSize());
        System.out.println("Number of male employees in the organization: " + MonsterCorp.getSizeByGender(Gender.Male));
        System.out.println("Number of female employees in the organization: " + MonsterCorp.getSizeByGender(Gender.Female));

        // List all employees
        System.out.println("List of all employees:");
        for (String name : MonsterCorp.allEmployees()) {
            System.out.println(name);
        }

        // Add the new functionality to count employees with an annual pay of 300.00
        // Note: You'll need to implement a method in OrganizationImpl to support this functionality.
        // The method might look like: MonsterCorp.getSize(m -> m.getAnnualPay() == 300.00);
        System.out.println("Number of employees with an annual pay of 300.00: " + 
            MonsterCorp.getSize(m -> m.getAnnualPay() == 300.00));
    }
}
