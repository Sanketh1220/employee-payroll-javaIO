/****************************************************
 * Purpose : Main Class created to Run a program
 *
 * @author Sanketh Chigurupalli
 * @version 1.0
 * @since 26-04-2021
 *
 ****************************************************/

package com.javaio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {

    // created an enum to stored final attributes
    public enum IOService {
        CONSOLE_IO,
        FILE_IO,
        REST_IO
    }

    // created a list
    private List<EmployeePayrollData> employeePayrollList;

    public EmployeePayrollService() {}

    /**
     * @param employeePayrollList
     */
    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList){
        this.employeePayrollList = employeePayrollList;
    }

    /**
     * Main Class to run program
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readingData();
        employeePayrollService.writingData(IOService.CONSOLE_IO);
    }

    /**
     * returns count od entries
     * @param fileIo
     * @return
     */
    public long countEntries(IOService fileIo) {
        if(fileIo.equals(IOService.FILE_IO)){
            return new EmployeePayRollFileIO().countEntries();
        }
        return 0;
    }

    /**
     * @param ioService
     */
    public void printData(IOService ioService){
        if (ioService.equals(IOService.FILE_IO)){
            new EmployeePayRollFileIO().printDataFromFile();
        }
    }

    /**
     * @param fileIo
     */
    public void writingData(IOService fileIo){
        if (fileIo.equals(IOService.CONSOLE_IO)){
            System.out.println(employeePayrollList);
        }else if (fileIo.equals(IOService.FILE_IO)){
            new EmployeePayRollFileIO().writeDataToFIle(employeePayrollList);
        }
    }

    /**
     * @param ioService
     */
    public void readDataFromFile(IOService ioService){
        if(ioService.equals(IOService.CONSOLE_IO)){
            new EmployeePayRollFileIO().readDataFromFile();
        }
    }

    /**
     * Method to read data
     */
    private void readingData() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Employee ID: ");
        int id = input.nextInt();
        System.out.println("Enter Employee Name: ");
        String name = input.next();
        System.out.println("Enter Employee Salary: ");
        double salary = input.nextDouble();

        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }
}