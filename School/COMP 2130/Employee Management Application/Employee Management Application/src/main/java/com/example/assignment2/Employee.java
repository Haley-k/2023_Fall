package com.example.assignment2;

import java.io.Serializable;

public abstract class Employee implements Serializable {
    protected static int idGenerator = 1000;
    protected int id;
    protected String name;
    protected String address;
    protected String phone;
    protected Department department;
    protected boolean isPartTime;
    protected boolean isFullTime;

    public abstract double getHourlyWage();
    public abstract double getBiweeklyBonus();

    //ID generator reads last id from load file
    public static void generateId(int idGenerator) {
        Employee.idGenerator = idGenerator;
    }

    //ID generator saves last id to save file
    public static int getIdGenerator() {
        return idGenerator;
    }

    public Employee(String name, String address, String phone, Department department) {
        this.id = ++idGenerator;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.department = department;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean isPartTime() {
        return isPartTime;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    //Separate toString for display information for application (Security purposes)
    public String displayEmp() {
        final StringBuilder sb = new StringBuilder("<Employee> ");
        sb.append("ID : ").append(id);
        sb.append(", Name : ").append(name);
        sb.append(", Address : ").append(address);
        sb.append(", Phone : ").append(phone);
        if (department != null)
            sb.append(", department : ").append(department.getName());

        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        if (department != null)
            sb.append(", department='").append(department.getName()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}