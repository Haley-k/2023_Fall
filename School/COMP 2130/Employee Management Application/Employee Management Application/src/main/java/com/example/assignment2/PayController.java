package com.example.assignment2;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class PayController {
    @FXML
    private ListView<String> calculatePayrollList = new ListView<>();
    @FXML
    private Stage primaryStage;
    @FXML
    private TextField PayrollEmpIdField;
    @FXML
    private TextField PayrollHoursWorkedField;
    @FXML
    private Text calcPayrollMessageLabel;
    Coordinator coor;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setData(Coordinator coor) {
        this.coor = coor;
    }

    public void displayText(Text label) {
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2.5));
        pauseTransition.setOnFinished(e -> label.setText("Message"));
        pauseTransition.play();
    }

    public void resetField() {
        PayrollEmpIdField.clear();
        PayrollHoursWorkedField.clear();
    }

    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }

    public void calcPayrollCalculateBtnClick(ActionEvent event) {
        String id = PayrollEmpIdField.getText();
        String hoursWorked = PayrollHoursWorkedField.getText();

        try {
            //Input Validation
            if (id.isEmpty() || hoursWorked.isEmpty()) {
                showAlert("Empty field!", "All the fields must be filled");
            } else if (!id.matches("^[1-9]\\d{3,}$")) {
                showAlert("Wrong Input!", "Please Enter Valid Employee ID");
            } else if (!hoursWorked.matches("^-?\\d+(\\.\\d+)?$")) {
                showAlert("Wrong Input!", "Hours of Worked must be numeric");
                //Generate payroll if all input is validated
            } else {
                Employee[] empList = coor.getEmpManager().getEmployeeList();
                for (int i = 0; i < coor.getEmpManager().getNumEmployee(); i++) {
                    if (empList[i].getId() == Integer.parseInt(id)) {
                        if(coor.generateEmpPayroll(Integer.parseInt(id), Double.parseDouble(hoursWorked))) {
                            calcPayrollMessageLabel.setText("Payroll Generated!");
                            displayText(calcPayrollMessageLabel);
                            resetField();
                            displayPayroll();
                            coor.saveData();
                        } else {
                            calcPayrollMessageLabel.setText("Payroll Could Not Generated!");
                            displayText(calcPayrollMessageLabel);
                        }
                    }
                }
            }
        } catch (NullPointerException | NumberFormatException e) {
            showAlert("Wrong Input!", "Check your Input");
        }
    }

    public void displayPayroll() {
        calculatePayrollList.getItems().clear();
        calculatePayrollList.getItems().add(coor.showPayroll(coor.getPayManager().getPayrollList()[coor.getPayManager().getNumPayroll() - 1].getId()));
    }

    public void calcPayrollBackBtnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent newNode = fxmlLoader.load();

        MainController controller = fxmlLoader.getController();
        controller.setData(coor);
        controller.setPrimaryStage(primaryStage);

        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(newNode);

        TabPane tabPane = (TabPane) currentScene.lookup("#tabPane");
        tabPane.getSelectionModel().select(2);
    }
}
