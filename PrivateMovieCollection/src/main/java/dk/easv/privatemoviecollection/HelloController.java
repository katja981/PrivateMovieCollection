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
import javafx.scene.control.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HelloController {

    @FXML
    private TextField searchField;

    @FXML
    private ChoiceBox<Category> genreFilter;

    @FXML
    private Slider ratingFilter;

    @FXML
    private Button applyFiltersButton;

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
        populateGenreFilter();

        applyFiltersButton.setOnAction(event -> applyFiltersAndSearch());

        searchButton.setOnAction(event -> applyFiltersAndSearch());

        searchField.textProperty().addListener((observable, oldValue, newValue) -> applyFiltersAndSearch());

    }

    private List<Movie> allMovies;

    private void loadMovies() {
        try {
            allMovies = movieManager.getAll();
        } catch (Exception e) {
        e.printStackTrace();
        allMovies = List.of();
        }

        Collections.sort(allMovies, Comparator.comparing(Movie::getMovieName));

        movieObservableList = FXCollections.observableList(allMovies);
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

    private void populateGenreFilter() {
        genreFilter.getItems().add(null);
        genreFilter.getItems().addAll(categoryObservableList);
        genreFilter.setValue(null);
    }

    private void applyFiltersAndSearch() {
        String searchTerm = searchField.getText().toLowerCase();
        Category selectedGenre = genreFilter.getValue();
        double minRating = ratingFilter.getValue();

        List<Movie> filteredMovies = allMovies.stream()
                .filter(movie -> searchTerm.isEmpty() || movie.getMovieName().toLowerCase().contains(searchTerm))
                .filter(movie -> selectedGenre == null || movie.getMovieCategory().equals(selectedGenre.getCategoryName()))
                .filter(movie -> movie.getImdbRating() >= minRating)
                .collect(Collectors.toList());

        movieListView.setItems(FXCollections.observableList(filteredMovies));

        if (filteredMovies.isEmpty()) {
            movieListView.setPlaceholder(new Label("No movies found"));
        }
    }
}