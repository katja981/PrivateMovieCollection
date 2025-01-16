package dk.easv.privatemoviecollection;

import BE.Category;
import BE.Movie;
import BLL.MovieManager;
import BLL.MovieSearch;
import DAL.CategoryDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HelloController {

    @FXML
    private TextField searchField;

    @FXML
    private ChoiceBox<Category> categoryFilter;

    @FXML
    private Slider ratingFilter;

    @FXML
    private Button applyFiltersButton;

    @FXML
    private Button searchButton;

    @FXML
    private ListView<Movie> movieListView;

    private ObservableList<Movie> movieObservableList;
    private MovieManager movieManager;
    private MovieSearch movieSearch;

    private List<Movie> allMovies;
    private String selectedCategory;
    private double selectedRating;

    @FXML
    private ListView<Category> categoryListView;

    private ObservableList<Category> categoryObservableList;

    @FXML
    public void initialize() {

        movieManager = new MovieManager();
        loadMovies();
        loadCategories();
        populateCategoryFilter();

        applyFiltersButton.setOnAction(event -> applyFilters());
        searchButton.setOnAction(event -> search());

    }

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
        categoryFilter.getItems().addAll(categories);
        categoryObservableList = FXCollections.observableList(categories);
        categoryListView.setItems(categoryObservableList);
    }

    private void populateCategoryFilter() {
        categoryFilter.getItems().add(null);
        categoryFilter.setValue(null);
    }

    @FXML
    private void applyFilters() {
        selectedCategory = categoryFilter.getValue() != null ? categoryFilter.getValue().getCategoryName() : null;
        selectedRating = ratingFilter.getValue();
        updateMovieListView();
    }

    public void search() {
        String searchText = searchField.getText();
        updateMovieListView();
    }

    private void updateMovieListView() {
        String searchTerm = searchField.getText();
        String selectedCategory = categoryFilter.getValue() != null ? categoryFilter.getValue().getCategoryName() : null;
        double minRating = ratingFilter.getValue();

        MovieSearch movieSearch = new MovieSearch(allMovies);
        List<Movie> filteredMovies = movieSearch.filterMovies(searchTerm, selectedCategory, minRating);

        if (filteredMovies.isEmpty()) {
            movieListView.setPlaceholder(new Label("No movies found"));
        } else {
            movieListView.setItems(FXCollections.observableArrayList(filteredMovies));
        }
    }
}