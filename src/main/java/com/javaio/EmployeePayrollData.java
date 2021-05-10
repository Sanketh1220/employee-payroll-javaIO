/****************************************************
 * Purpose : Program is written handle the employee-payroll data
 *
 * @author Sanketh Chigurupalli
 * @version 1.0
 * @since 26-04-2021
 *
 ****************************************************/

package com.javaio;

/**
 * Created a POJO
 */
public class EmployeePayrollData {
    public int id;
    public String name;
    public double salary;

    /**
     *
     * @param id
     * @param name
     * @param salary
     */
    public EmployeePayrollData(Integer id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    /**
     * getters and setters initialized
     * @return
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Method to read the employee data
     * @return
     */
    public String toString() {
        return "Employee Id = " + id + ", Name ='" + name + '\'' + ", Salary = " + salary;
    }
}
