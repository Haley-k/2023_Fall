package com.example.assignment2;

import java.io.Serializable;

public class DepartmentManager implements Serializable {
    private int maxDepartment;
    private int numDepartment;
    private Department[] departmentList;

    public DepartmentManager(int maxDepartment) {
        this.maxDepartment = maxDepartment;
        this.numDepartment = 0;
        this.departmentList = new Department[maxDepartment];
    }

    public boolean addDepartment(String name, int maxEmployee) {
        if (numDepartment < maxDepartment) {
            departmentList[numDepartment] = new Department(name, maxEmployee);
            numDepartment++;
            return true;
        }
        return false;
    }

    public int getMaxDepartment() {
        return maxDepartment;
    }

    public void setMaxDepartment(int maxDepartment) {
        this.maxDepartment = maxDepartment;
    }

    public int getNumDepartment() {
        return numDepartment;
    }

    public void setNumDepartment(int numDepartment) {
        this.numDepartment = numDepartment;
    }

    public Department[] getDepartmentList() {
        return departmentList;
    }

    private int search(int id) {
        for (int i = 0; i < numDepartment; i++) {
            if (departmentList[i].getId() == id)
                return i;
        }
        return -1;
    }

    public Department findDepartment(int id) {
        int loc = search(id);

        if (loc != -1)
            return departmentList[loc];

        return null;
    }

    public boolean addEmployeeToDepartment(int depId, Employee employee) {
        Department dep = findDepartment(depId);

        if (dep.getNumEmployee() < dep.getMaxEmployee()) {
            for (int i = 0; i < dep.getNumEmployee(); i++) {
                //Cannot assign same employee to same department
                if (dep.getEmployeeList()[i].getId() == employee.getId())
                    return false;
            }
            dep.getEmployeeList()[dep.getNumEmployee()] = employee;
            dep.setNumEmployee(dep.getNumEmployee() + 1);
            return true;
        }
        return false;
    }

    public boolean deleteEmployeeFromDepartment(int depId, Employee employee) {
        Department dep = findDepartment(depId);

        for (int i = 0; i < dep.getNumEmployee(); i++) {
            if (dep.getEmployeeList()[i].getId() == employee.getId()) {
                dep.getEmployeeList()[i] = dep.getEmployeeList()[dep.getNumEmployee() - 1];
                dep.getEmployeeList()[dep.getNumEmployee() - 1] = null;
                dep.setNumEmployee(dep.getNumEmployee() - 1);
                return true;
            }
        }
        return false;
    }

    public boolean deleteDepartment(int id) {
        int loc = search(id);

        if (loc != -1) {
            departmentList[loc] = departmentList[numDepartment - 1];
            departmentList[numDepartment - 1] = null;
            numDepartment--;

            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("********** All Department list **********\n");

        for (int i = 0; i < numDepartment; i++)
            sb.append(departmentList[i].toString()).append("\n");

        return sb.toString();
    }
}
