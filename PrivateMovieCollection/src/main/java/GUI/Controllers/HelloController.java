package GUI.Controllers;

import BE.Category;
import BE.Movie;
import DAL.CategoryDAO;
import DAL.MovieDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class HelloController {

    @FXML
    private ListView<Movie> movieListView;

    private ObservableList<Movie> movieObservableList;

    @FXML
    private ListView<Category> categoryListView;

    private ObservableList<Category> categoryObservableList;

    @FXML
    private Button addMovieButton;

    @FXML
    public void initialize() {
        loadMovies();
        loadCategories();
    }

    private void loadMovies() {
        MovieDAO movieDAO = new MovieDAO();
        List<Movie> movies;

        try {
            movies = movieDAO.getAllMovies();
        } catch (Exception e) {
            e.printStackTrace();
            movies = List.of();
        }

        movieObservableList = FXCollections.observableList(movies);
        movieListView.setItems(movieObservableList);
    }

    private void loadCategories() {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories;

        try {
            categories = categoryDAO.getAllCategories();
        } catch (Exception e) {
            e.printStackTrace();
            categories = List.of();
        }

        categoryObservableList = FXCollections.observableList(categories);
        categoryListView.setItems(categoryObservableList);
    }

    @FXML
    void onAddMovieCLicked(ActionEvent event) {
        try {
            // Loads the AddAMovie FXML File
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/privatemoviecollection/AddAMovie.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            //Creates a new stage for the "Add Movie" dialog
            Stage stage = new Stage();
            stage.setTitle("Add Movie");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Blocks interaction with main screen
            stage.showAndWait(); //Wait until the "Add Movie" screen is closed
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}