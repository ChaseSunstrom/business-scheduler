package main.java.com.scheduler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Main application class to launch the JavaFX scheduler UI.
 */
public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        // use FileInputStream for loading FXML
        String address = "src/main/resources/com/scheduler/main.fxml";
        Parent root;
        try (InputStream fxmlStream = new FileInputStream(address)) {
            root = loader.load(fxmlStream);
        }
        primaryStage.setTitle("Intelligent Scheduler");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
} 