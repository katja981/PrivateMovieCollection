package GUI.Controllers;

import BE.Category;
import BE.Movie;
import DAL.CategoryDAO;
import DAL.MovieDAO;
import BLL.MovieManager;
import BLL.MovieSearch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    @FXML
    private ListView<Category> categoryListView;
    @FXML
    private Button addMovieButton;

    private ObservableList<Movie> movieObservableList;
    private ObservableList<Category> categoryObservableList;

    private MovieManager movieManager;
    private MovieSearch movieSearch;

    private List<Movie> allMovies;
    private String selectedCategory;
    private double selectedRating;

    @FXML
    private ComboBox<String> sortByComboBox;

    @FXML
    private ListView<Movie> moviesListView;

    @FXML
    public void initialize() {
        movieManager = new MovieManager();
        loadMovies();
        loadCategories();
        applyFiltersButton.setOnAction(event -> applyFilters());
        searchButton.setOnAction(event -> search());

        ratingFilter.setMin(0);
        ratingFilter.setMax(10);
        ratingFilter.setValue(5);

        ratingFilter.setBlockIncrement(1);
        ratingFilter.setMajorTickUnit(1);
        ratingFilter.setMinorTickCount(0);
        ratingFilter.setSnapToTicks(true);

        movies.setAll(movieDAO.getAllMovies());
        movieListView.setItems(movies);

        sortByComboBox.getItems().addAll("Title", "IMDB Rating", "Category");

        sortByComboBox.setOnAction(this::onSortBy);
    }

    private final ObservableList<Movie> movies = FXCollections.observableArrayList();
    private final MovieDAO movieDAO = new MovieDAO();

    private void loadMovies() {
        MovieDAO movieDAO = new MovieDAO();
        List<Movie> movies;

        try {
            allMovies = movieManager.getAll();
            System.out.println("Loaded movies: " + allMovies.size());
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

        categoryFilter.getItems().add(null);
        categoryFilter.getItems().addAll(categories);
        categoryFilter.setValue(null);

        categoryObservableList = FXCollections.observableList(categories);
        categoryListView.setItems(categoryObservableList);
    }

    @FXML
    private void applyFilters() {
        selectedCategory = categoryFilter.getValue() != null ? categoryFilter.getValue().getCategoryName() : null;
        selectedRating = ratingFilter.getValue();
        updateMovieListView();
    }

    public void search() {
        String searchText = searchField.getText();
        System.out.println("Searching for: " + searchText);

        if (searchText == null || searchText.trim().isEmpty()) {
            // If the search field is empty, show all movies (reset the filter)
            System.out.println("Search field is empty, showing all movies");
            updateMovieListView(); // This will reset the movie list view to show all movies
        } else {
            // If there's a search term, apply the filter
            System.out.println("Performing search with term: " + searchText);
            updateMovieListView(); // This will filter movies based on the current search term
        }
    }

    private void updateMovieListView() {
        String searchTerm = searchField.getText();
        String selectedCategory = categoryFilter.getValue() != null ? categoryFilter.getValue().getCategoryName() : null;
        double minRating = ratingFilter.getValue();

        System.out.println("Filtering with searchTerm: " + searchTerm + ", Category: " + selectedCategory + ", Min Rating: " + minRating);

        MovieSearch movieSearch = new MovieSearch(allMovies);
        List<Movie> filteredMovies = movieSearch.filterMovies(searchTerm, selectedCategory, minRating);

        if (filteredMovies.isEmpty()) {
            movieListView.setItems(FXCollections.observableArrayList());
            movieListView.setPlaceholder(new Label("No movies found"));
        } else {
            movieListView.setItems(FXCollections.observableArrayList(filteredMovies));
            movieListView.setPlaceholder(null);
        }
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

    @FXML
    public void onSortBy(ActionEvent event) {
        String selectedOption = sortByComboBox.getValue();
        if (selectedOption != null) {
            sortList(selectedOption);
        }
    }

    private void sortList(String criteria) {
        switch (criteria) {
            case "Title":
                FXCollections.sort(movies, Comparator.comparing(Movie::getMovieName));
                break;
            case "IMDB Rating":
                FXCollections.sort(movies, Comparator.comparing(Movie::getImdbRating).reversed());
                break;
            case "Category":
                FXCollections.sort(movies, Comparator.comparing(Movie::getPersonalRating));
                break;
        }
    }
}