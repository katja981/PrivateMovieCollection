package GUI.Models;

import BE.Category;
import BE.Movie;
import BLL.CategoryManager;
import BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayerModel {
    private final MovieManager movieManager;
    private final CategoryManager categoryManager;

    private ObservableList<Movie> movieObservableList = FXCollections.observableArrayList();
    private ObservableList<Category> categoryObservableList = FXCollections.observableArrayList();

    public PlayerModel() {
        movieManager = new MovieManager();
        movieObservableList.setAll(movieManager.getAll());
        categoryManager = new CategoryManager();
        categoryObservableList.setAll(categoryManager.getAll());
    }

    public ObservableList<Movie> getMovieObservableList() {
        return movieObservableList;
    }

    public void addMovie(Movie movie) {
        movieObservableList.add(movie);
    }
}