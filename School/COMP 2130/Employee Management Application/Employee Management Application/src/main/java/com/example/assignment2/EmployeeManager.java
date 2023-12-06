package com.example.assignment2;

import java.io.Serializable;

public class EmployeeManager implements Serializable {
    private int maxEmployee;
    private int numEmployee;
    private Employee[] employeeList;

    public EmployeeManager(int maxEmployee) {
        this.maxEmployee = maxEmployee;
        this.numEmployee = 0;
        this.employeeList = new Employee[maxEmployee];
    }

    //Add Part Time Employee
    public boolean addEmployee(String name, String address, String phone, Department department, double wage) {
        if (numEmployee < maxEmployee) {
            employeeList[numEmployee] = new PartTime(name, address, phone, department, wage);
            numEmployee++;
            return true;
        }
        return false;
    }

    //Add Full Time Employee
    public boolean addEmployee(String name, String address, String phone, Department department, double annualSalary, double annualBonus) {
        if (numEmployee < maxEmployee) {
            employeeList[numEmployee] = new FullTime(name, address, phone, department, annualSalary, annualBonus);
            numEmployee++;
            return true;
        }
        return false;
    }

    public int getMaxEmployee() {
        return maxEmployee;
    }

    public void setMaxEmployee(int maxEmployee) {
        this.maxEmployee = maxEmployee;
    }

    public int getNumEmployee() {
        return numEmployee;
    }

    public void setNumEmployee(int numEmployee) {
        this.numEmployee = numEmployee;
    }

    public Employee[] getEmployeeList() {
        return employeeList;
    }

    private int search(int id) {
        for (int i = 0; i < numEmployee; i++) {
            if (employeeList[i].getId() == id)
                return i;
        }
        return -1;
    }

    public Employee findEmployee(int id) {
        int loc = search(id);

        if (loc != -1)
            return employeeList[loc];

        return null;
    }

    public boolean deleteEmployee(int id) {
        int loc = search(id);

        if (loc != -1) {
            employeeList[loc] = employeeList[numEmployee - 1];
            employeeList[numEmployee - 1] = null;
            numEmployee--;

            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("---- All Employee list ----\n");

        for (int i = 0; i < numEmployee; i++)
            sb.append(employeeList[i].displayEmp()).append("\n");

        return sb.toString();
    }
}