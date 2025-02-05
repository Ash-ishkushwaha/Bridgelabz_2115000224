
class Employee {
    private static String companyName = "Tech Corp";
    private static int totalEmployees = 0;
    
    private final String id;
    private String name;
    private String designation;
    
    public Employee(String name, String id, String designation) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        totalEmployees++;
    }
    
    public static void displayTotalEmployees() {
        System.out.println("Total Employees: " + totalEmployees);
    }
    
    public void displayEmployeeDetails() {
        if (this instanceof Employee) {
            System.out.println("Company: " + companyName);
            System.out.println("Name: " + this.name);
            System.out.println("ID: " + this.id);
            System.out.println("Designation: " + this.designation);
        } else {
            System.out.println("Invalid employee object.");
        }
    }
    
    public static void main(String[] args) {
        Employee emp1 = new Employee("Alice Johnson", "EMP001", "Software Engineer");
        Employee emp2 = new Employee("Bob Smith", "EMP002", "Data Analyst");
        
        emp1.displayEmployeeDetails();
        emp2.displayEmployeeDetails();
        
        Employee.displayTotalEmployees();
    }
}

