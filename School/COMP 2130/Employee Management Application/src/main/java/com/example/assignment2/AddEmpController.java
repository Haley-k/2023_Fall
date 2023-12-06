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

public class AddEmpController {
    @FXML private Stage primaryStage;
    @FXML private TextField empNameField;
    @FXML private TextField empAddressField;
    @FXML private TextField empPhoneField;
    @FXML private TextField empPayField;
    @FXML private TextField empBonusField;
    @FXML private Text addEmpMessageLabel;
    @FXML private ChoiceBox<String> empTypeChoiceBox;
    @FXML private ChoiceBox<String> empDepartmentChoiceBox;
    Coordinator coor;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setData(Coordinator coor) {
        this.coor = coor;
        initializeChoiceBox();
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
        empTypeChoiceBox.setValue(null);
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
        empTypeChoiceBox.setItems(FXCollections.observableArrayList("Full Time", "Part Time"));
        empDepartmentChoiceBox.setItems(depNames);

        empTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //Make 'bonus' field disable if user chose 'Part Time' option
            if ("Full Time".equals(newValue))
                empBonusField.setDisable(false);
            else
                empBonusField.setDisable(true);
        });
    }

    public void addEmpAddBtnClick() {
        String name = empNameField.getText();
        String address = empAddressField.getText();
        String phone = empPhoneField.getText();
        String type = empTypeChoiceBox.getValue();
        String dep = empDepartmentChoiceBox.getValue();
        Department department = null;
        String pay = empPayField.getText();
        double bonus = 0;

        try {
            //Get the department to add to employee if user chose department
            if (dep != null) {
                for (int i = 0; i < coor.getDepManager().getNumDepartment(); i++) {
                    if (coor.getDepManager().getDepartmentList()[i].getName().equals(dep))
                        department = coor.getDepManager().getDepartmentList()[i];
                }
            }

            //Input Validation
            if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || type == null || pay.isEmpty()) {
                showAlert("Empty field!", "All the fields must be filled");
                //Alphabet and whitespace only
            } else if (!name.matches("^[a-zA-Z\\s]+")) {
                showAlert("Wrong Input!", "Name can only contain alphabet and whitespace");
                //Integer only, length must be 10
            } else if (!phone.matches("\\d+") || phone.length() != 10) {
                showAlert("Wrong Input!", "Phone must be 10 digit and all numeric");
                //Any rational number
            } else if (!pay.matches("^-?\\d+(\\.\\d+)?$")) {
                showAlert("Wrong Input!", "Pay must be numeric");
                //Add Employee if all input is validated
            } else {
                if (!empBonusField.getText().isEmpty())
                    bonus = Double.parseDouble(empBonusField.getText());

                boolean empAdded = false;

                if (type.equals("Full Time"))
                    empAdded = coor.addFullTime(name, address, phone, department, Double.parseDouble(pay), bonus);
                else if (type.equals("Part Time"))
                    empAdded = coor.addPartTime(name, address, phone, department, Double.parseDouble(pay));

                //If employee was added, update employee to department if user chose department,
                // display the message and save the data
                if (empAdded) {
                    if (department != null)
                        coor.updateEmpDepartment(coor.getEmpManager().getEmployeeList()[coor.getEmpManager().getNumEmployee() - 1].getId(), department.getId());

                    addEmpMessageLabel.setText("Employee Added!");
                    displayText(addEmpMessageLabel);
                    resetField();
                    coor.saveData();
                } else {
                    addEmpMessageLabel.setText("Employee Could Not Added!");
                    displayText(addEmpMessageLabel);
                }
            }
        } catch (NullPointerException | NumberFormatException e) {
            showAlert("Wrong Input!", "Check your Input");
        }
    }

    public void empResetBtnClick() {
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