package dk.easv.privatemoviecollection;

import BE.Category;
import BE.Movie;
import DAL.CategoryDAO;
import DAL.MovieDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class HelloController {

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
}