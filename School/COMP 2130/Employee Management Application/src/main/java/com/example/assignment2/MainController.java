package com.example.assignment2;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class MainController {

    @FXML private ListView<String> empList = new ListView<>();
    @FXML private ListView<String> depList = new ListView<>();
    @FXML private ListView<String> payrollList = new ListView<>();
    @FXML private Stage primaryStage;
    @FXML private TextField empIdField;
    @FXML private TextField depIdField;
    @FXML private TextField payrollIdField;
    @FXML private Text empMessageLabel;
    @FXML private Text depMessageLabel;
    @FXML private Text payrollMessageLabel;
    private Coordinator coor;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setData(Coordinator coor) {
        this.coor = coor;
    }

    public void displayText(Text label) {
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
        pauseTransition.setOnFinished(e -> label.setText("Message"));
        pauseTransition.play();
    }

    public void empAddBtnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addEmployee.fxml"));
        Parent newNode = fxmlLoader.load();

        AddEmpController controller = fxmlLoader.getController();
        controller.setData(coor);
        controller.setPrimaryStage(primaryStage);

        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(newNode);
    }

    public void empViewBtnClick() {
        empList.getItems().clear();
        empList.getItems().add(coor.showAllEmployee());
    }

    public void empModifyBtnClick(ActionEvent event) throws IOException {
        String empIdText = empIdField.getText();

        try {
            //Check if user entered ID which is greater than 1000 (Employee ID format)
            if (empIdText.matches("^[1-9]\\d{3,}$")) {
                int empId = Integer.parseInt(empIdText);

                //If user ID validated, check if employee is full/part time, and open the form accordingly
                if (coor.getIsFullTime(empId)) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyFullTimeEmployee.fxml"));
                    Parent newNode = fxmlLoader.load();

                    ModifyFullEmpController controller = fxmlLoader.getController();
                    controller.setData(coor);
                    controller.setEmpId(empId);
                    controller.setPrimaryStage(primaryStage);

                    Scene currentScene = ((Node) event.getSource()).getScene();
                    currentScene.setRoot(newNode);
                } else if (coor.getIsPartTime(empId)) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyPartTimeEmployee.fxml"));
                    Parent newNode = fxmlLoader.load();

                    ModifyPartEmpController controller = fxmlLoader.getController();
                    controller.setData(coor);
                    controller.setEmpId(empId);
                    controller.setPrimaryStage(primaryStage);

                    Scene currentScene = ((Node) event.getSource()).getScene();
                    currentScene.setRoot(newNode);
                } else {
                    empMessageLabel.setText("Employee not found!");
                    displayText(empMessageLabel);
                }
            } else {
                empMessageLabel.setText("Please Enter Valid Employee ID!");
                displayText(empMessageLabel);
            }
        } catch (NullPointerException e) {
            empMessageLabel.setText("Please Enter Valid Employee ID!");
            displayText(empMessageLabel);
        }
    }

    public void empDeleteBtnClick() {
        String empIdText = empIdField.getText();

        //ID validation
        if (empIdText.matches("^[1-9]\\d{3,}$")) {
            if (coor.deleteEmployee(Integer.parseInt(empIdText))) {
                empIdField.clear();
                empMessageLabel.setText("Employee Deleted!");
                displayText(empMessageLabel);
            } else {
                empIdField.clear();
                empMessageLabel.setText("Employee Could Not Deleted!");
                displayText(empMessageLabel);
            }
        } else {
            empMessageLabel.setText("Please Enter Valid Employee ID!");
            displayText(empMessageLabel);
        }
    }

    public void depAddBtnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addDepartment.fxml"));
        Parent newNode = fxmlLoader.load();

        AddDepController controller = fxmlLoader.getController();
        controller.setData(coor);
        controller.setPrimaryStage(primaryStage);

        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(newNode);
    }

    public void depViewBtnClick() {
        depList.getItems().clear();
        String depIdText = depIdField.getText();

        //Check if user entered ID which is greater than 100 (Department ID format)
        if (depIdText.matches("^[1-9]\\d{2,}$")) {
            depIdField.clear();
            depList.getItems().add(coor.showDepartment(Integer.parseInt(depIdText)));
        }
        //Display all departments if user did not enter any ID
        else if (depIdText.isEmpty())
            depList.getItems().add(coor.showAllDepartment());
        else {
            depMessageLabel.setText("Please Enter Valid Department ID or Leave it Empty to See All Departments");
            displayText(depMessageLabel);
        }
    }

    public void depDeleteBtnClick() {
        String depIdText = depIdField.getText();

        //ID validation
        if (depIdText.matches("^[1-9]\\d{2,}$")) {
            if (coor.deleteDepartment(Integer.parseInt(depIdText))) {
                depIdField.clear();
                depMessageLabel.setText("Department Deleted!");
                displayText(depMessageLabel);
            } else {
                depIdField.clear();
                depMessageLabel.setText("Department Could Not Deleted!");
                displayText(depMessageLabel);
            }
        } else {
            depMessageLabel.setText("Please Enter Valid Department ID!");
            displayText(depMessageLabel);
        }
    }

    public void payrollCalculateBtnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("calculatePayroll.fxml"));
        Parent newNode = fxmlLoader.load();

        PayController controller = fxmlLoader.getController();
        controller.setData(coor);
        controller.setPrimaryStage(primaryStage);

        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(newNode);
    }

    public void payrollViewDepBtnClick() {
        payrollList.getItems().clear();
        String payIdText = payrollIdField.getText();

        try {
            if (payIdText.matches("^[1-9]\\d{2,}$")) {
                payrollIdField.clear();
                payrollList.getItems().add(coor.generateDepPayroll(Integer.parseInt(payIdText)));
            } else {
                payrollMessageLabel.setText("Please Enter Valid Department ID!");
                displayText(payrollMessageLabel);
            }
        } catch (NullPointerException e) {
            payrollMessageLabel.setText("Please Enter Valid Department ID!");
            displayText(payrollMessageLabel);
        }
    }

    public void payrollViewBtnClick() {
        payrollList.getItems().clear();
        payrollList.getItems().add(coor.showAllPayroll());
    }

    public void payrollViewEmpBtnClick() {
        try {
            payrollList.getItems().clear();
            String payIdText = payrollIdField.getText();

            if (payIdText.matches("^[1-9]\\d{3,}$")) {
                payrollIdField.clear();
                payrollList.getItems().add(coor.showEmpPayroll(Integer.parseInt(payIdText)));
            } else {
                payrollMessageLabel.setText("Please Enter Valid Employee ID!");
                displayText(payrollMessageLabel);
            }
        } catch (NullPointerException e) {
            payrollMessageLabel.setText("Please Enter Valid Employee ID!");
            displayText(payrollMessageLabel);
        }
    }
}