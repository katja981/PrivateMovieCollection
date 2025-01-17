package GUI.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import BE.Movie;
import BLL.MovieManager;

public class DeleteMovieController {

    @FXML
    private ListView<Movie> movieListView;

    @FXML
    private Button btnDelete;

    // Reference to the shared movie list
    private ObservableList<Movie> movieList;

    public void initialize() {
        // Get the shared movie list from the MovieManager
        movieList = MovieManager.getSharedMovieList();

        // Set the list in the ListView
        movieListView.setItems(movieList);
    }

    @FXML
    private void handleDeleteMovie() {
        // Get the selected movie from the ListView
        Movie selectedMovie = movieListView.getSelectionModel().getSelectedItem();

        if (selectedMovie != null) {
            // Remove the selected movie from the list
            movieList.remove(selectedMovie);

            // Show confirmation
            showAlert(AlertType.INFORMATION, "Movie Deleted",
                    "The movie has been successfully deleted.");
        } else {
            // Show warning if no movie is selected
            showAlert(AlertType.WARNING, "No Movie Selected",
                    "Please select a movie to delete.");
        }
    }


    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
