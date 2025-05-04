/*
    @author Chase Sunstrom casunstro@dmacc.edu
    @date 2025-05-04
    @version 1.0

    This is the main application class for the Business Scheduler.
    It launches the JavaFX scheduler UI.
*/

package main.java.com.scheduler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.InputStream;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

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
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        // fade-in animation on launch
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
} 