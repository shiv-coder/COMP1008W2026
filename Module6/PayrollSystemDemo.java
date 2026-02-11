import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
 
/*
    Module 6 Activity: Polymorphism in Payroll System
    Covers:
    - Runtime Polymorphism (Method Overriding)
    - Compile-time Polymorphism (Method Overloading)
    - Interface-based Polymorphism
    - Collections (ArrayList)
    - Encapsulation﻿
*/
 
 
// STEP 1: Define Payable interface
interface Payable {
    double getPaymentAmount(); // all classes implementing this must define
}
 
 
// STEP 2: Abstract Employee superclass
abstract class Employee implements Payable {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
 
 
    // Constructor
    public Employee(String first, String last, String ssn) {
        // TODO: initialize fields
        this.firstName = first;
        this.lastName = last;
        this.socialSecurityNumber = ssn;
    }
 
 
    // Getters
    // TODO: add getFirstName(), getLastName(), getSocialSecurityNumber()
      public String getFirstName() { return firstName; }
      public String getLastName() { return lastName; }
      public String getSocialSecurityNumber() { return socialSecurityNumber; }
 
    // Runtime polymorphism: earnings() will be overridden
    public abstract double earnings();
 
 
    // Compile-time polymorphism: overloaded method examples
    public double calculateBonus(double amount) {
        // TODO: implement simple bonus calculation
        
        return amount * 0.10;//(10% bonus)
    }
 
 
    public double calculateBonus(double amount, int months) {
        // TODO: implement bonus calculation over multiple months
        return amount * 0.10 * months;//cumulative bonus for multiple months
    }
 
 
    @Override
    public double getPaymentAmount() {
        // TODO: return earnings()
        return earnings();
    }
 
 
    @Override
    public String toString() {
        // TODO: return formatted employee info
        return firstName + " " + lastName + "\nSSN: " + socialSecurityNumber;
    }
}
 
 
// STEP 3: Concrete subclasses
 
 
class SalariedEmployee extends Employee {
    private double weeklySalary;
 
 
    public SalariedEmployee(String first, String last, String ssn, double salary) {
        super(first, last, ssn);
        // TODO: validate and set weeklySalary
        if(salary >= 0.0)
            this.weeklySalary = salary;
        else
            this.weeklySalary = 0.0;

    }
 
 
    @Override
    public double earnings() {
        // TODO: return weeklySalary
        return weeklySalary;
    }
 
 
    @Override
    public String toString() {
        // TODO: include "Salaried Employee" info plus super.toString()
        return "Salaried Employee: " + super.toString() + "\nWeekly Salary: " + weeklySalary;
    }
}
 
 
class HourlyEmployee extends Employee {
    private double wage;   // wage per hour
    private double hours;  // hours worked
 
 
    public HourlyEmployee(String first, String last, String ssn, double wage, double hours) {
        super(first, last, ssn);
        // TODO: validate and set wage and hours
        this.wage = wage >= 0.0 ? wage : 0.0;
        this.hours = (hours >= 0.0 && hours <=168) ? hours : 0.0;//max 168 hours/week
    }
 
 
    @Override
    public double earnings() {
        // TODO: calculate earnings
        if(hours < 40){
            return wage * hours;
        }
        else
            return 40 * wage + (hours-40) * wage * 1.5;
    }
 
 
    @Override
    public String toString() {
        // TODO: include "Hourly Employee" info plus super.toString()
         return "Hourly Employee: " + super.toString() +
                "\nHourly Wage: " + wage + "\nHours Worked: " + hours;
    }
}
 
 
class CommissionEmployee extends Employee {
    private double grossSales;
    private double commissionRate;
 
 
    public CommissionEmployee(String first, String last, String ssn, double sales, double rate) {
        super(first, last, ssn);
        // TODO: validate and set grossSales and commissionRate
        this.grossSales = (sales >= 0.0) ? sales: 0.0;
        this.commissionRate = (rate >0.0 && rate < 1.0) ? rate : 0.0;
    }
 
 
    @Override
    public double earnings() {
        // TODO: return grossSales * commissionRate
        return grossSales * commissionRate;
    }
 
 
    @Override
    public String toString() {
        // TODO: include "Commission Employee" info plus super.toString()
         return "Comission Employee: " + super.toString() +
                "\nGross Sales: " + grossSales + "\nComissionRate: " + commissionRate;
    }
}
 
 
class BasePlusCommissionEmployee extends CommissionEmployee {
    private double baseSalary;
 
 
    public BasePlusCommissionEmployee(String first, String last, String ssn,
                                      double sales, double rate, double salary) {
        super(first, last, ssn, sales, rate);
        // TODO: validate and set baseSalary
        this.baseSalary = (salary > 0.0) ? salary :0.0;
    }
 
    public double getBaseSalary(){
        return baseSalary;
    }

    
    @Override
    public double earnings() {
        // TODO: return baseSalary + super.earnings()
        return getBaseSalary() + super.earnings();
    }
    
    public void setBaseSalary(double salary){
        if(salary > 0.0){
            this.baseSalary = salary;
        }
    }
 
 
    @Override
    public String toString() {
        // TODO: include "Base salary" info plus super.toString()
        return "Base Plus Comission Employee: " + super.toString() +
                "\nGBase Salary: " + baseSalary;
    }
}
 
 
// STEP 4: Invoice class demonstrating interface polymorphism
class Invoice implements Payable {
    private String partNumber;
    private String partDescription;
    private int quantity;
    private double pricePerItem;
 
 
    public Invoice(String part, String desc, int qty, double price) {
        // TODO: initialize fields
        this.partNumber = part;
        this.partDescription = desc;
        this.quantity = (qty > 0) ? qty :0;
        this.pricePerItem = (price > 0.0) ? price : 0.0;
    }
 
 
    @Override
    public double getPaymentAmount() {
        // TODO: return quantity * pricePerItem
        return quantity * pricePerItem;
    }
 
 
    @Override
    public String toString() {
        // TODO: return formatted invoice info
        return "Invoice: " + partNumber + " (" + partDescription + ")" +
                "\nQuantity: " + quantity + "\nPrice/Item: " + pricePerItem;
    }
}
 
 
// STEP 5: Main driver class
public class PayrollSystemDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
 
        // TODO: Create a list of Payable objects (ArrayList)
        List<Payable> payableList = new ArrayList<>();
 
 
        // TODO: Add Employee subclasses and Invoice objects to the list
        payableList.add(new SalariedEmployee("Josh", "Doe", "111-11-1111", 1000.0));
        payableList.add(new HourlyEmployee("Jane", "Smith", "222-22-2222", 20.0, 45));
        payableList.add(new CommissionEmployee("Jim", "Brown", "333-33-3333", 5000.0, 0.05));
        payableList.add(new BasePlusCommissionEmployee("Jill", "White", "444-44-4444", 4000.0, 0.04, 300.0));
        
        payableList.add(new Invoice("123", ":Laptop", 2, 800.0));
        payableList.add(new Invoice("456", "Mouse", 5, 20.0));

        System.out.println("PAYROLL SYSTEM OUTPUT:\n");
 
        // TODO: Loop through list and polymorphically call getPaymentAmount()
        for (Payable payable : payableList) {
            System.out.println(payable.toString());
            System.out.println("Payment: " + payable.getPaymentAmount());
        
 
 
        // TODO: Demonstrate compile-time polymorphism with calculateBonus()
        if(payable instanceof Employee){
            Employee emp = (Employee) payable;
            System.out.printf("Bonus for Employee: $%.2f%n",emp.calculateBonus(1000.0));
             System.out.printf("Bonus for Employee: $%.2f%n",emp.calculateBonus(1000.0, 3));
        }
        System.out.println("----------------------------------------");
        }
    }
    }
