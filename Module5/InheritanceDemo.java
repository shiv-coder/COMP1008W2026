import java.util.Scanner;
 
 
/*
    Module 5 Solution: Inheritance Example with CommissionEmployee
*/
 
 
abstract class Employee {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
 

    public Employee(String first, String last, String ssn) {
        this.firstName = first;
        this.lastName = last;
        this.socialSecurityNumber = ssn;
    }
 
 
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getSocialSecurityNumber() { return socialSecurityNumber; }
 
 
    public abstract double earnings();
 
 
    @Override
    public String toString() {
        return String.format("%s %s%nsocial security number: %s",
                getFirstName(), getLastName(), getSocialSecurityNumber());//commission employee :[firstname] [lastName]
                //social security number : [ssn]
    }
}
 /*
 String name = "Shivi";
 System.out.printf("Hello %s",name);
 Hello Shivi
 
 */
 
class CommissionEmployee extends Employee {
    private double grossSales;
    private double commissionRate;
 
 
    public CommissionEmployee(String first, String last, String ssn,
                              double sales, double rate) {
        super(first, last, ssn);
        setGrossSales(sales);
        setCommissionRate(rate);
    }
 
 
    public void setGrossSales(double sales) {
        if (sales < 0.0) throw new IllegalArgumentException(
                "Gross sales must be >= 0.0");
        grossSales = sales;
    }
 
 
    public void setCommissionRate(double rate) {
        if (rate <= 0.0 || rate >= 1.0) throw new IllegalArgumentException(
                "Commission rate must be > 0.0 and < 1.0");
        commissionRate = rate;
    }
 
 
    public double getGrossSales() { return grossSales; }
    public double getCommissionRate() { return commissionRate; }
 
 
    @Override
    public double earnings() {
        return getCommissionRate() * getGrossSales();
    }
 
 
    @Override
    public String toString() {
        return String.format("%s%n%s: $%,.2f; %s: %.2f",
                "commission employee: " + super.toString(),
                "gross sales", getGrossSales(),//$10,000.00,$1,234,567.89
                "commission rate", getCommissionRate());
    }
}
 
 
class BasePlusCommissionEmployee extends CommissionEmployee {
    private double baseSalary;
 
 
    public BasePlusCommissionEmployee(String first, String last, String ssn,
                                      double sales, double rate, double salary) {
        super(first, last, ssn, sales, rate);
        setBaseSalary(salary);
    }
 
 
    public void setBaseSalary(double salary) {
        if (salary < 0.0)
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        baseSalary = salary;
    }
 
 
    public double getBaseSalary() { return baseSalary; }
 
 
    @Override
    public double earnings() {
        return getBaseSalary() + super.earnings();
    }
 
 
    @Override
    public String toString() {
        return String.format("base-salaried %s%n%s: $%,.2f",
                super.toString(), "base salary", getBaseSalary());
    }
}
 
 
public class InheritanceDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
 
 
        CommissionEmployee ce = new CommissionEmployee(
                "Sue", "Jones", "333-33-3333", 10000, .06);
        BasePlusCommissionEmployee bpce = new BasePlusCommissionEmployee(
                "Bob", "Lewis", "444-44-4444", 5000, .04, 300);
 
 
        System.out.printf("%s%nearned: $%,.2f%n%n",
                ce.toString(), ce.earnings());
        System.out.printf("%s%nearned: $%,.2f%n",
                bpce.toString(), bpce.earnings());
 
 
        input.close();
    }
}