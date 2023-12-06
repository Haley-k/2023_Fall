package com.example.assignment2;

import java.io.Serializable;

public class PayrollManager implements Serializable {
    private int maxPayroll;
    private int numPayroll;
    private Payroll[] payrollList;

    public PayrollManager(int maxPayroll) {
        this.maxPayroll = maxPayroll;
        this.numPayroll = 0;
        this.payrollList = new Payroll[maxPayroll];
    }

    public boolean addPayroll(double hoursOfWork, Employee employee) {
        if (numPayroll < maxPayroll) {
            payrollList[numPayroll] = new Payroll(hoursOfWork, employee);
            numPayroll++;
            return true;
        }
        return false;
    }

    public int getMaxPayroll() {
        return maxPayroll;
    }

    public void setMaxPayroll(int maxPayroll) {
        this.maxPayroll = maxPayroll;
    }

    public int getNumPayroll() {
        return numPayroll;
    }

    public void setNumPayroll(int numPayroll) {
        this.numPayroll = numPayroll;
    }

    public Payroll[] getPayrollList() {
        return payrollList;
    }

    private int search(int id) {
        for (int i = 0; i < numPayroll; i++) {
            if (payrollList[i].getId() == id)
                return i;
        }
        return -1;
    }

    public Payroll findPayroll(int id) {
        int loc = search(id);

        if (loc != -1)
            return payrollList[loc];

        return null;
    }

    public String findAllPayrollForEmp(int id) {
        StringBuilder payroll = new StringBuilder();
        for (int i = 0; i < numPayroll; i++) {
            if (payrollList[i].getEmployee().getId() == id)
                payroll.append("\n").append(payrollList[i].toString());
        }
        return payroll.toString();
    }

    public boolean deletePayroll(int id) {
        int loc = search(id);

        if (loc != -1) {
            payrollList[loc] = payrollList[numPayroll - 1];
            payrollList[numPayroll - 1] = null;
            numPayroll--;

            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("---- All Payroll list ----\n");

        for (int i = 0; i < numPayroll; i++)
            sb.append(payrollList[i].toString()).append("\n");

        return sb.toString();
    }
}