/****************************************************
 * Purpose : Program to created a File for storing employee-payroll data
 *
 * @author Sanketh Chigurupalli
 * @version 1.0
 * @since 26-04-2021
 *
 ****************************************************/

package com.javaio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayRollFileIO {

    // Creating a file name
    public static String PAYROLL_FILE_NAME = "payrollFileInfo.txt";

    /**
     * Method to write data to file
     * @param employeePayrollDataList
     */
    public void writeDataToFIle(List<EmployeePayrollData> employeePayrollDataList) {
        StringBuffer stringBuffer = new StringBuffer();
        employeePayrollDataList.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            stringBuffer.append(employeeDataString);
        });
        try {
            Files.write(Paths.get(PAYROLL_FILE_NAME), stringBuffer.toString().getBytes());
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    /**
     * Method to read data from file
     */
    public void readDataFromFile(){
        List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
        try {
            Files.lines(new File(PAYROLL_FILE_NAME).toPath()).map(line -> line.trim())
                    .forEach(line -> System.out.println(line));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Method to print data from the file
     */
    public void printDataFromFile() {
        try {
            Files.lines(new File(PAYROLL_FILE_NAME).toPath()).forEach(System.out::println);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Method to count and return number entries into File
     * @return
     */
    public long countEntries() {
        long entries = 0;
        try {
            entries = Files.lines(new File(PAYROLL_FILE_NAME).toPath()).count();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return entries;
    }
}
