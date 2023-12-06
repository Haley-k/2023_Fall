package com.example.assignment2;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Coordinator implements Serializable {
    private static final String EMPLOYEE_DATA_PATH = "employee.data";
    private static final String DEPARTMENT_DATA_PATH = "department.data";
    private static final String PAYROLL_DATA_PATH = "payroll.data";
    private static final String EMPLOYEE_ID_PATH = "employeeId.data";
    private static final String DEPARTMENT_ID_PATH = "departmentId.data";
    private static final String PAYROLL_ID_PATH = "payrollId.data";

    private EmployeeManager empManager;
    private DepartmentManager depManager;
    private PayrollManager payManager;

    public Coordinator(EmployeeManager empManager, DepartmentManager depManager, PayrollManager payManager) {
        this.empManager = empManager;
        this.depManager = depManager;
        this.payManager = payManager;
    }

    public void setEmpManager(EmployeeManager empManager) {
        this.empManager = empManager;
    }

    public void setDepManager(DepartmentManager depManager) {
        this.depManager = depManager;
    }

    public void setPayManager(PayrollManager payManager) {
        this.payManager = payManager;
    }

    public DepartmentManager getDepManager() {
        return depManager;
    }

    public EmployeeManager getEmpManager() {
        return empManager;
    }

    public PayrollManager getPayManager() {
        return payManager;
    }

    public boolean addFullTime(String name, String address, String phone, Department department, double annualSalary, double annualBonus) {
        return empManager.addEmployee(name, address, phone, department, annualSalary, annualBonus);
    }

    public boolean addPartTime(String name, String address, String phone, Department department, double wage) {
        return empManager.addEmployee(name, address, phone, department, wage);
    }

    public boolean addDepartment(String name, int maxEmployee) {
        return depManager.addDepartment(name, maxEmployee);
    }

    public boolean updateEmpName(int empId, String name) {
        Employee emp = empManager.findEmployee(empId);

        if (emp != null) {
            emp.setName(name);
            return true;
        }
        return false;
    }

    public boolean updateEmpAddress(int empId, String address) {
        Employee emp = empManager.findEmployee(empId);

        if (emp != null) {
            emp.setAddress(address);
            return true;
        }
        return false;
    }

    public boolean updateEmpPhone(int empId, String phone) {
        Employee emp = empManager.findEmployee(empId);

        if (emp != null) {
            emp.setPhone(phone);
            return true;
        }
        return false;
    }

    public boolean updateEmpDepartment(int empId, int depId) {
        Employee emp = empManager.findEmployee(empId);
        Department dep = depManager.findDepartment(depId);

        if (emp != null && dep != null) {
            if (emp.getDepartment() != null)
                //Delete employee from current department
                depManager.deleteEmployeeFromDepartment(emp.getDepartment().getId(), emp);

            //Add employee to new department
            if (depManager.addEmployeeToDepartment(depId, emp)) {
                emp.setDepartment(dep);
                return true;
            }
        }
        return false;
    }

    public boolean updatePartTimeEmpWage(int empId, double wage) {
        PartTime partEmp = (PartTime) empManager.findEmployee(empId);

        if (partEmp != null) {
            partEmp.setWage(wage);
            return true;
        }
        return false;
    }

    public boolean updateFullTimeEmpSalary(int empId, double salary) {
        FullTime fullEmp = (FullTime) empManager.findEmployee(empId);

        if (fullEmp != null) {
            fullEmp.setAnnualSalary(salary);
            return true;
        }
        return false;
    }

    public boolean updateFullTimeEmpBonus(int empId, double bonus) {
        FullTime fullEmp = (FullTime) empManager.findEmployee(empId);

        if (fullEmp != null) {
            fullEmp.setAnnualBonus(bonus);
            return true;
        }
        return false;
    }

    public boolean getIsPartTime(int empId) {
        return empManager.findEmployee(empId).isPartTime();
    }

    public boolean getIsFullTime(int empId) {
        return empManager.findEmployee(empId).isFullTime();
    }

    public boolean deleteEmployee(int empId) {
        Employee emp = empManager.findEmployee(empId);

        if (emp != null) {
            //Delete employee from department if employee is assigned to any department
            if (emp.getDepartment() != null) {
                int depId = emp.getDepartment().getId();
                depManager.deleteEmployeeFromDepartment(depId, emp);
                empManager.deleteEmployee(empId);
                return true;
            } else {
                empManager.deleteEmployee(empId);
                return true;
            }
        }
        return false;
    }

    public boolean deleteDepartment(int depId) {
        Department dep = depManager.findDepartment(depId);

        if (dep != null) {
            //Delete all employees from department before delete department if department was not empty
            Employee[] empList = dep.getEmployeeList();

            for (int i = 0; i < dep.getNumEmployee(); i++)
                empManager.findEmployee(empList[i].getId()).setDepartment(null);

            return depManager.deleteDepartment(depId);
        }
        return false;
    }

    public boolean deletePayroll(int payId) {
        return payManager.deletePayroll(payId);
    }

    public String showEmployee(int empId) {
        if (empManager.findEmployee(empId) != null)
            return empManager.findEmployee(empId).toString();
        return "Cannot find Employee!";
    }

    public String showDepartment(int depId) {
        if (depManager.findDepartment(depId) != null)
            return depManager.findDepartment(depId).toString();
        return "Cannot find Department!";
    }

    public String showEmpPayroll(int empId) {
        if (empManager.findEmployee(empId) != null) {
            StringBuilder result = new StringBuilder("===== Payroll for ");
            result.append(empManager.findEmployee(empId).getName()).append(" =====");
            result.append(payManager.findAllPayrollForEmp(empId)).append("\n");

            return result.toString();
        }
        return "Cannot find Employee!";
    }

    public String showPayroll(int payId) {
        if (payManager.findPayroll(payId) != null)
            return payManager.findPayroll(payId).toString();
        return "Cannot find Payroll!";
    }

    public String showAllEmployee() {
        return empManager.toString();
    }

    public String showAllDepartment() {
        return depManager.toString();
    }

    public String showAllPayroll() {
        return payManager.toString();
    }

    public boolean generateEmpPayroll(int empId, double hoursOfWork) {
        Employee emp = empManager.findEmployee(empId);

        if (emp != null) {
            return payManager.addPayroll(hoursOfWork, emp);
        }
        return false;
    }

    public String generateDepPayroll(int depId) {
        Department dep = depManager.findDepartment(depId);
        StringBuilder result = new StringBuilder("====== Payroll for ");
        result.append(dep.getName()).append(" Department ======");

        if (dep != null) {
            Employee[] empList = dep.getEmployeeList();
            for (int i = 0; i < dep.getNumEmployee(); i++) {
                if (payManager.findAllPayrollForEmp(empList[i].getId()) != null)
                    result.append(payManager.findAllPayrollForEmp(empList[i].getId()));
            }
            return result.toString();
        }
        return "Cannot find Department!";
    }

    public void loadData() {
        Path empPath = Paths.get(EMPLOYEE_DATA_PATH);
        Path depPath = Paths.get(DEPARTMENT_DATA_PATH);
        Path payPath = Paths.get(PAYROLL_DATA_PATH);

        try {
            if (!Files.exists(empPath) || !Files.exists(depPath) || !Files.exists(payPath))
                //Creating a new Coordinator with default managers if file is not exist
                new Coordinator(new EmployeeManager(1000), new DepartmentManager(1000), new PayrollManager(1000));
            else {
                setEmpManager(FileHandlingManager.loadEmployeeManager(EMPLOYEE_DATA_PATH));
                setDepManager(FileHandlingManager.loadDepartmentManager(DEPARTMENT_DATA_PATH));
                setPayManager(FileHandlingManager.loadPayrollManager(PAYROLL_DATA_PATH));

                Employee.generateId(FileHandlingManager.loadIdGenerator(EMPLOYEE_ID_PATH));
                Department.generateId(FileHandlingManager.loadIdGenerator(DEPARTMENT_ID_PATH));
                Payroll.generateId(FileHandlingManager.loadIdGenerator(PAYROLL_ID_PATH));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        try {
            FileHandlingManager.save(empManager, EMPLOYEE_DATA_PATH);
            FileHandlingManager.save(depManager, DEPARTMENT_DATA_PATH);
            FileHandlingManager.save(payManager, PAYROLL_DATA_PATH);

            FileHandlingManager.saveIdGenerator(Employee.getIdGenerator(), EMPLOYEE_ID_PATH);
            FileHandlingManager.saveIdGenerator(Department.getIdGenerator(), DEPARTMENT_ID_PATH);
            FileHandlingManager.saveIdGenerator(Payroll.getIdGenerator(), PAYROLL_ID_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}