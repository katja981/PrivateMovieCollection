package GUI.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddMovieController {

    @FXML
    private TextField txtMovieName;

    @FXML
    private TextField txtMovieRating;

    @FXML
    private TextField txtMovieCategory;

    @FXML
    private TextField txtFilePath;
    private List<String> selectedCategories = new ArrayList<>();
    @FXML
    private Button btnChooseCategory;

    @FXML
    private Button btnChooseFile;

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

    @FXML
    private void onChooseCategoryButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/privatemoviecollection/ChooseCategories.fxml"));
            Parent root = loader.load();

            ChooseCategoriesController controller = loader.getController();
            controller.setSelectedCategories(new ArrayList<>(selectedCategories));

            // Opens the ChooseCategories window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Choose Categories");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            selectedCategories = controller.getSelectedCategories();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onChooseFileClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Movie File");

        File documentsFolder = new File(System.getProperty("user.home"), "Downloads");
        if(documentsFolder.exists()) {
            fileChooser.setInitialDirectory(documentsFolder);
        }

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Video Files (*.mp4, *.mpeg4)", "*.mp4", "*.mpeg4");
        fileChooser.getExtensionFilters().add(filter);

        Stage stage = (Stage) btnChooseFile.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if(selectedFile != null) {
            txtFilePath.setText(selectedFile.getAbsolutePath());
        }
    }
}
