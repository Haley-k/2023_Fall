package com.example.assignment2;

import java.io.Serializable;

public class Department implements Serializable {
    private static int idGenerator = 100;
    private int id;
    private String name;
    private int maxEmployee;
    private int numEmployee;
    private Employee[] employeeList;

    //ID generator reads last id from load file
    public static void generateId(int idGenerator) {
        Department.idGenerator = idGenerator;
    }

    //ID generator saves last id to save file
    public static int getIdGenerator() {
        return idGenerator;
    }

    public Department(String name, int maxEmployee) {
        this.id = ++idGenerator;
        this.name = name;
        this.maxEmployee = maxEmployee;
        this.numEmployee = 0;
        this.employeeList = new Employee[maxEmployee];
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("======= Department =======\n");

        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'').append("\n");
        sb.append("---- Employee list ----\n");

        for (int i = 0; i < numEmployee; i++)
            sb.append(employeeList[i].displayEmp()).append("\n");

        return sb.toString();
    }
}