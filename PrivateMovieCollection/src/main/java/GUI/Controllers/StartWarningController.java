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
    private Button continueButton;

    @FXML
    private Button cancelButton;

    // This method handles the "Continue" button click

    @FXML
    private void OnContinueClick(ActionEvent actionEvent) {
        try {
            // Load the main application screen (the screen after StartWarning)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/MainScreen.fxml"));
            Parent root = loader.load();

            // Create a new Stage for the main application screen
            Stage primaryStage = (Stage) continueButton.getScene().getWindow(); // Get current window (StartWarning)
            primaryStage.setScene(new Scene(root)); // Set the scene to the main screen

            // Optionally set the title of the main screen
            primaryStage.setTitle("Main Application");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // This method handles the "Cancel" button click (optional)
    @FXML
    private void handleCancelAction() {
        System.out.println("Cancel button clicked!");
        // Close the current window (StartWarning)
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Label Remember;

    @FXML
    private Button btnContinue;

    @FXML
    private Label rating;

    @FXML
    void onContinueClick(ActionEvent event) {

    }

}
