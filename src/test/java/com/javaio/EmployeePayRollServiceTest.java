/****************************************************
 * Purpose : Test file written to test the employee-payroll program
 *
 * @author Sanketh Chigurupalli
 * @version 1.0
 * @since 26-04-2021
 *
 ****************************************************/

package com.javaio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class EmployeePayRollServiceTest {

    /**
     * Test written to match employee entries when written to file
     */
    @Test
    public void given3Employees_WhenWriitenToFile_ShouldMatchEmployeeEntries() {
        EmployeePayrollData[] arrayOfEmployees = {
                new EmployeePayrollData(1, "Sanketh Chigurupalli", 400000.00),
                new EmployeePayrollData(2, "Soumya Goud", 200000.00),
                new EmployeePayrollData(3, "Sundeep Babbur", 300000.00)
        };
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmployees));
        employeePayrollService.writingData(EmployeePayrollService.IOService.FILE_IO);
        employeePayrollService.printData(EmployeePayrollService.IOService.FILE_IO);
        long entries = employeePayrollService.countEntries(EmployeePayrollService.IOService.FILE_IO);
        System.out.println("No.of entries into file are: " + entries);

        Assertions.assertEquals(3, entries);
    }

    /**
     * Test written to match match count entries by reading file
     */
    @Test
    public void givenFile_OnReadingFromFile_ShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readDataFromFile(EmployeePayrollService.IOService.FILE_IO);
        long entries = employeePayrollService.countEntries(EmployeePayrollService.IOService.FILE_IO);

        Assertions.assertEquals(3, entries);
    }
}
