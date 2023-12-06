package com.example.assignment2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Coordinator coor = new Coordinator(new EmployeeManager(1000), new DepartmentManager(1000), new PayrollManager(1000));

    @Override
    public void start(Stage stage) throws IOException {
        coor.loadData();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
        Parent root = fxmlLoader.load();

        MainController controller = fxmlLoader.getController();
        controller.setData(coor);
        controller.setPrimaryStage(stage);

        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        coor.saveData();
    }
}