package com.example.assignment2;

import java.io.Serializable;

public class PartTime extends Employee implements Serializable {
    private double wage;

    public PartTime(String name, String address, String phone, Department department, double wage) {
        super(name, address, phone, department);
        this.wage = wage;
        this.isPartTime = true;
        this.isFullTime = false;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    @Override
    public double getHourlyWage() {
        return getWage();
    }

    @Override
    public double getBiweeklyBonus() {
        return 0;
    }

    //Separate toString for display information for application (Security purpose)
    public String displayEmp() {
        final StringBuilder sb = new StringBuilder("<Part Time> ");
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
        final StringBuilder sb = new StringBuilder("PartTime{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        if (department != null)
            sb.append(", department='").append(department.getName()).append('\'');
        sb.append(", wage=").append(wage);
        sb.append('}');
        return sb.toString();
    }
}