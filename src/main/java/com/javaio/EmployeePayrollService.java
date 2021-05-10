package com.javaio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {

    public enum IOService {
        CONSOLE_IO,
        FILE_IO,
        REST_IO
    }

    private List<EmployeePayrollData> employeePayrollList;

    public EmployeePayrollService() {}

    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList){
        this.employeePayrollList = employeePayrollList;
    }

    public static void main(String[] args) {
        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readingData();
        employeePayrollService.writingData(IOService.CONSOLE_IO);
    }

    public long countEntries(IOService fileIo) {
        if(fileIo.equals(IOService.FILE_IO)){
            return new EmployeePayRollFileIO().countEntries();
        }
        return 0;
    }

    public void writingData(IOService fileIo){
        if (fileIo.equals(IOService.CONSOLE_IO)){
            System.out.println(employeePayrollList);
        }else if (fileIo.equals(IOService.FILE_IO)){
            new EmployeePayRollFileIO().writeDataToFIle(employeePayrollList);
        }
    }

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