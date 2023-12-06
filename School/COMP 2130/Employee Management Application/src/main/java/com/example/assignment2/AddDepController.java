package com.example.assignment2;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class AddDepController {
    @FXML private Stage primaryStage;
    @FXML private TextField addDepNameField;
    @FXML private TextField addDepMaxPplField;
    @FXML private Text addDepMessageLabel;
    Coordinator coor;

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

    public void resetField() {
        addDepNameField.clear();
        addDepMaxPplField.clear();
    }

    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }

    public void addDepAddBtnClick() {
        String name = addDepNameField.getText();
        String maxPpl = addDepMaxPplField.getText();

        //Name or MaxPeople cannot be empty
        if (name.isEmpty() || maxPpl.isEmpty()) {
            showAlert("Empty field!", "All the fields must be filled");
            //Checks if name contains alphabet only
            } else if (!name.matches("^[a-zA-Z\\s]+")) {
            showAlert("Wrong Input!", "Name can only contain alphabet and whitespace");
            //Checks if max people contains integer only
            } else if (!maxPpl.matches("\\d+")) {
            showAlert("Wrong Input!", "Max People must be numeric");
            //Add Department if all input is validated
            } else {
            try {
                if (coor.addDepartment(name, Integer.parseInt(maxPpl))) {
                    addDepMessageLabel.setText("Department Added!");
                    displayText(addDepMessageLabel);
                    resetField();
                    coor.saveData();
                } else {
                    addDepMessageLabel.setText("Department Could Not Added!");
                    displayText(addDepMessageLabel);
                }
            } catch (NullPointerException | NumberFormatException e) {
                showAlert("Wrong Input!", "Check your Input");
            }
        }
    }

    public void addDepBackBtnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent newNode = fxmlLoader.load();

        MainController controller = fxmlLoader.getController();
        controller.setData(coor);
        controller.setPrimaryStage(primaryStage);

        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(newNode);

        TabPane tabPane = (TabPane) currentScene.lookup("#tabPane");
        tabPane.getSelectionModel().select(1);
    }
}
