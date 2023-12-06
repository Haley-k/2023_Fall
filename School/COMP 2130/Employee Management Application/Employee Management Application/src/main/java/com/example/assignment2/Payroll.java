package com.example.assignment2;

import java.io.Serializable;
import java.time.LocalDate;

public class Payroll implements Serializable {
    private final double INCOME_TAX_RATE = 0.11;
    private final double CPP_RATE = 0.05;
    private final double EI_RATE =0.02;
    private final double STANDARD_HOURS_WORK = 80; //Biweekly
    private final double OVERTIME_RATE = 1.5;
    private static int idGenerator = 0;
    private int id;
    private double hoursOfWork;
    private LocalDate date;
    private Employee employee;

    //ID generator reads last id from load file
    public static void generateId(int idGenerator) {
        Payroll.idGenerator = idGenerator;
    }

    //ID generator saves last id to save file
    public static int getIdGenerator() {
        return idGenerator;
    }

    public Payroll(double hoursOfWork, Employee employee) {
        this.id = ++idGenerator;
        this.hoursOfWork = hoursOfWork;
        this.date = LocalDate.now();
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public double getHoursOfWork() {
        return hoursOfWork;
    }

    public void setHoursOfWork(double hoursOfWork) {
        this.hoursOfWork = hoursOfWork;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double calcOvertimePay() {
        if (getHoursOfWork() > STANDARD_HOURS_WORK)
            return (getHoursOfWork() - STANDARD_HOURS_WORK) * employee.getHourlyWage() * OVERTIME_RATE;
        else
            return 0;
    }

    public double payrollBeforeDeductions() {
        if (getHoursOfWork() > STANDARD_HOURS_WORK)
            return employee.getHourlyWage() * STANDARD_HOURS_WORK + calcOvertimePay() + employee.getBiweeklyBonus();
        else
            return employee.getHourlyWage() * getHoursOfWork() + employee.getBiweeklyBonus();
    }

    public double incomeTax() {
        return payrollBeforeDeductions() * INCOME_TAX_RATE;
    }
    public double cpp() {
        return payrollBeforeDeductions() * CPP_RATE;
    }
    public double ei() {
        return payrollBeforeDeductions() * EI_RATE;
    }
    public double payrollAfterDeductions() {
        return payrollBeforeDeductions() - incomeTax() - cpp() - ei();
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("employee ID # ").append(employee.getId());
        sb.append("\t\tEmployee Name : ").append(employee.getName());
        sb.append("\nHours of Work : ").append(getHoursOfWork());
        sb.append("\tPayroll Generate Date : ").append(date);
        sb.append("\nEarning $").append(String.format("%.2f", payrollBeforeDeductions()));
        sb.append(" (With Overtime Earning of $").append(String.format("%.2f", calcOvertimePay())).append(")");
        sb.append("\nIncome Tax -$").append(String.format("%.2f", incomeTax()));
        sb.append("\nCPP -$").append(String.format("%.2f", cpp()));
        sb.append("\nEI -$").append(String.format("%.2f", ei()));
        sb.append("\nNet Income $").append(String.format("%.2f", payrollAfterDeductions())).append("\n");

        return sb.toString();
    }
}
