package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StartWarningController {

    @FXML
    private Button btnContinue;


    @FXML
    private void onContinueClick(ActionEvent actionEvent) {
        try {
            // Load the main application screen (the screen after StartWarning)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/privatemoviecollection/hello-view.fxml"));
            Parent root = loader.load();

            // Create a new Stage for the main application screen
            Stage primaryStage = (Stage) btnContinue.getScene().getWindow(); // Get current window (StartWarning)
            primaryStage.setScene(new Scene(root)); // Set the scene to the main screen

            // Optionally set the title of the main screen
            primaryStage.setTitle("Main Application");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private Label Remember;

    @FXML
    private Label rating;
}

