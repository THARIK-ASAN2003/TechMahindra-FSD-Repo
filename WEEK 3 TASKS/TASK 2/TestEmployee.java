import java.util.Scanner;

class Employee {
    private int empId;
    private String empName;
    private double sal;
    
    // Set employee details
    public void setEmployeeDetails(int empId, String empName, double sal) {
        this.empId = empId;
        this.empName = empName;
        this.sal = sal;
    }
    
    // Display employee details
    public void getEmployeeDetails() {
        System.out.println("Employee ID: " + empId);
        System.out.println("Employee Name: " + empName);
        System.out.println("Salary: " + sal);
    }
    
    // Determine loan eligibility based on annual salary thresholds
    public double getLoanEligibility() {
        if (sal >= 1500000)
            return 700000;
        else if (sal >= 1000000)
            return 500000;
        else if (sal >= 600000)
            return 200000;
        else
            return 0;
    }
}

public class TestEmployee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee emp = new Employee();
        
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Employee Name: ");
        String name = sc.next();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        
        emp.setEmployeeDetails(id, name, salary);
        emp.getEmployeeDetails();
        
        double loan = emp.getLoanEligibility();
        if (loan > 0)
            System.out.println("Eligible Loan Amount: " + loan);
        else
            System.out.println("Not eligible for a loan.");
        
        sc.close();
    }
}