package fa.training.model;

public class Employee {

    private int employee_id;
    private String employeeName;
    private double salary;
    private int spvrld;

    public Employee() {
    }

    public Employee(int employee_id, String employeeName, double salary, int spvrld) {
        this.employee_id = employee_id;
        this.employeeName = employeeName;
        this.salary = salary;
        this.spvrld = spvrld;
    }

    public int getEmployeeId() {
        return employee_id;
    }

    public void setEmployeeId(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getSpvrld() {
        return spvrld;
    }

    public void setSpvrld(int spvrld) {
        this.spvrld = spvrld;
    }

}
