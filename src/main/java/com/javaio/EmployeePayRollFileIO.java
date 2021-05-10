package com.javaio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EmployeePayRollFileIO {
    public static String PAYROLL_FILE_NAME = "payrollFileInfo.txt";

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
