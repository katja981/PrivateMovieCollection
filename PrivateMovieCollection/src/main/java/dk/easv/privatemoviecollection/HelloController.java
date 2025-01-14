package dk.easv.privatemoviecollection;

import BE.Category;
import BE.Movie;
import DAL.CategoryDAO;
import DAL.MovieDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.util.List;
import java.util.stream.Collectors;

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

    @FXML
    public void initialize() {
        loadMovies();
        loadCategories();

        searchButton.setOnAction(event -> performSearch());

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                movieListView.setItems(movieObservableList);
            }
        });
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

    private void performSearch() {
        String searchTerm = searchField.getText().toLowerCase();

        List<Movie> filteredMovies = movieObservableList.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());

        movieListView.setItems(FXCollections.observableList(filteredMovies));

        if (filteredMovies.isEmpty()) {
            movieListView.setPlaceholder(new javafx.scene.control.Label("No results found."));
        }
    }
}