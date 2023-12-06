package com.example.assignment2;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;

public class ModifyFullEmpController {
    @FXML private Stage primaryStage;
    @FXML private TextField empNameField;
    @FXML private TextField empAddressField;
    @FXML private TextField empPhoneField;
    @FXML private TextField empPayField;
    @FXML private TextField empBonusField;
    @FXML private Text modifyFullEmpMessageLabel;
    @FXML private ChoiceBox<String> empDepartmentChoiceBox;
    Coordinator coor;
    private int empId;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setData(Coordinator coor) {
        this.coor = coor;
        initializeChoiceBox();
    }

    public void setEmpId(int id) {
        this.empId = id;
    }

    public void displayText(Text label) {
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
        pauseTransition.setOnFinished(e -> label.setText("Message"));
        pauseTransition.play();
    }

    public void resetField() {
        empNameField.clear();
        empAddressField.clear();
        empPhoneField.clear();
        empPayField.clear();
        empBonusField.clear();
        empDepartmentChoiceBox.setValue(null);
    }

    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }

    private void initializeChoiceBox() {
        ObservableList<String> depNames = FXCollections.observableArrayList("");

        //Get the department list to add to choiceBox
        if (coor != null) {
            for (int i = 0; i < coor.getDepManager().getNumDepartment(); i++)
                depNames.add(coor.getDepManager().getDepartmentList()[i].getName());
        }
        empDepartmentChoiceBox.setItems(depNames);
    }

    public void empModifyBtnClick(ActionEvent event) {
        String name = empNameField.getText();
        String address = empAddressField.getText();
        String phone = empPhoneField.getText();
        String dep = empDepartmentChoiceBox.getValue();
        Department department = null;
        String pay = empPayField.getText();
        String bonus = empBonusField.getText();
        boolean changed = false;

        try {
            //Input Validation
            if (name.isEmpty() && address.isEmpty() && phone.isEmpty() && pay.isEmpty()) {
                modifyFullEmpMessageLabel.setText("Please fill any blank you wish to modify");
                displayText(modifyFullEmpMessageLabel);
            }

            //If any field is filled by user, validate the input and change employee's information accordingly
            if (!name.isEmpty() && name.matches("^[a-zA-Z\\s]+")) {
                coor.updateEmpName(empId, name);
                changed = true;
            }
            if (!address.isEmpty()) {
                coor.updateEmpAddress(empId, address);
                changed = true;
            }
            if (phone.matches("\\d+") && phone.length() == 10) {
                coor.updateEmpPhone(empId, phone);
                changed = true;
            }
            if (dep != null) {
                for (int i = 0; i < coor.getDepManager().getNumDepartment(); i++) {
                    if (coor.getDepManager().getDepartmentList()[i].getName().equals(dep))
                        department = coor.getDepManager().getDepartmentList()[i];
                }
                coor.updateEmpDepartment(empId, department.getId());
                changed = true;
            }
            if (!pay.isEmpty() && pay.matches("^-?\\d+(\\.\\d+)?$")) {
                coor.updateFullTimeEmpSalary(empId, Double.parseDouble(pay));
                changed = true;
            }
            if (!bonus.isEmpty() && bonus.matches("^-?\\d+(\\.\\d+)?$")) {
                coor.updateFullTimeEmpBonus(empId, Double.parseDouble(bonus));
                changed = true;
            }

            //If change was made, display the message and save the data
            if (changed) {
                modifyFullEmpMessageLabel.setText("Information Changed!");
                displayText(modifyFullEmpMessageLabel);
                resetField();
                coor.saveData();
            }
        } catch (NullPointerException | NumberFormatException e) {
            showAlert("Wrong Input!", "Check your Input");
        }
    }

    public void empResetBtnClick(ActionEvent event) {
        resetField();
    }

    public void empBackBtnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent newNode = fxmlLoader.load();

        MainController controller = fxmlLoader.getController();
        controller.setData(coor);
        controller.setPrimaryStage(primaryStage);

        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(newNode);
    }
}