package dk.easv.privatemoviecollection;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMovieController {

    @FXML
    private TextField txtMovieName;

    @FXML
    private TextField txtMovieRating;

    @FXML
    private TextField txtMovieCategory;

    @FXML
    private TextField txtFilePath;

    @FXML
    void onAddButtonClicked() {
        //Get values from the fields
        String movieName = txtMovieName.getText();
        try {
            String ratingInput = txtMovieRating.getText();
            float movieRating = Float.parseFloat(ratingInput);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String movieCategory = txtMovieCategory.getText();
        String movieFilePath = txtFilePath.getText();

        //Closes the dialog
        Stage stage = (Stage) txtMovieName.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onCancelButtonClicked() {
        //Closes the dialog
        Stage stage = (Stage) txtMovieName.getScene().getWindow();
        stage.close();
    }
}
