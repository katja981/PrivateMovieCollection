package dk.easv.privatemoviecollection;

import BE.Category;
import BE.Movie;
import BLL.MovieManager;
import BLL.MovieSearch;
import DAL.CategoryDAO;
import DAL.MovieDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HelloController {

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private ListView<Movie> movieListView;

    private ObservableList<Movie> movieObservableList;

    @FXML
    private ListView<Category> categoryListView;

    private ObservableList<Category> categoryObservableList;

    private MovieManager movieManager; // Manages all movie-related operations
    private MovieSearch movieSearch;

    @FXML
    public void initialize() {

        movieManager = new MovieManager();

        loadMovies();
        loadCategories();

        searchButton.setOnAction(event -> performSearch());

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                loadMovies();
            }
        });
    }

    private void loadMovies() {

        List<Movie> movies;

        try {
            movies = movieManager.getAll();
        } catch (Exception e) {
        e.printStackTrace();
        movies = List.of();
        }

        Collections.sort(movies, Comparator.comparing(Movie::getMovieName));

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

        Collections.sort(categories, Comparator.comparing(Category::getCategoryName));

        categoryObservableList = FXCollections.observableList(categories);
        categoryListView.setItems(categoryObservableList);
    }

    private void performSearch() {
        String searchTerm = searchField.getText();

        if (searchTerm == null || searchTerm.isBlank()) {
            loadMovies(); // Reload all movies if the search field is empty
            return;
        }

        // Search movies using MovieManager or MovieDAO
        MovieDAO movieDAO = new MovieDAO();
        List<Movie> filteredMovies;

        try {
            filteredMovies = movieDAO.getAllMovies().stream()
                    .filter(movie -> movie.getMovieName().toLowerCase().contains(searchTerm.toLowerCase()))
                    .toList(); // Keep the filtered results in scope
        } catch (Exception e) {
            e.printStackTrace();
            filteredMovies = List.of(); // Empty list if an error occurs
        }

        movieObservableList = FXCollections.observableList(filteredMovies);
        movieListView.setItems(movieObservableList);
    }
}