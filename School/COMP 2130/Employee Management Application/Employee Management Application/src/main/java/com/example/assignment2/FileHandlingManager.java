package com.example.assignment2;

import java.io.*;

public class FileHandlingManager {
    public static void save(Object data, String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        try (oos) {
            oos.writeObject(data);

            oos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Object load(String filePath) throws IOException, ClassNotFoundException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Invalid file path");
        }

        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try (ois) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static EmployeeManager loadEmployeeManager(String filePath) {
        try {
            return (EmployeeManager) load(filePath);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new EmployeeManager(1000);  //Default manager if loading fails
        }
    }

    public static DepartmentManager loadDepartmentManager(String filePath) {
        try {
            return (DepartmentManager) load(filePath);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new DepartmentManager(1000);  //Default manager if loading fails
        }
    }

    public static PayrollManager loadPayrollManager(String filePath) {
        try {
            return (PayrollManager) load(filePath);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new PayrollManager(1000);  //Default manager if loading fails
        }
    }

    public static void saveIdGenerator(int idGenerator, String filePath) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(filePath);
        DataOutputStream dos = new DataOutputStream(fos);

        try (dos) {
            dos.writeInt(idGenerator);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int loadIdGenerator(String filePath) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        DataInputStream dis = new DataInputStream(fis);

        try (dis) {
            return dis.readInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}