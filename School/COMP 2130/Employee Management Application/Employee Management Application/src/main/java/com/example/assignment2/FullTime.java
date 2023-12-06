package com.example.assignment2;

import java.io.Serializable;

public class FullTime extends Employee implements Serializable {
    private double annualSalary;
    private double annualBonus;

    public FullTime(String name, String address, String phone, Department department, double annualSalary, double annualBonus) {
        super(name, address, phone, department);
        this.annualSalary = annualSalary;
        this.annualBonus = annualBonus;
        this.isPartTime = false;
        this.isFullTime = true;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public double getAnnualBonus() {
        return annualBonus;
    }

    public void setAnnualBonus(double annualBonus) {
        this.annualBonus = annualBonus;
    }

    public double getHourlyWage() {
        return annualSalary / 52 / 40;
    }

    @Override
    public double getBiweeklyBonus() {
        return annualBonus / 26;
    }

    //Separate toString for display information for application (Security purpose)
    public String displayEmp() {
        final StringBuilder sb = new StringBuilder("<Full Time> ");
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
        final StringBuilder sb = new StringBuilder("FullTime{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        if (department != null)
            sb.append(", department='").append(department.getName()).append('\'');
        sb.append(", annualSalary=").append(annualSalary);
        sb.append(", annualBonus=").append(annualBonus);
        sb.append('}');
        return sb.toString();
    }
}