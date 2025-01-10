package dk.easv.privatemoviecollection;

import BE.Movie;
import BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

public class PlayerModel {
    private final MovieManager movieManager;

    private ObservableList<Movie> movieObservableList = FXCollections.observableArrayList();

    public PlayerModel() {
        movieManager = new MovieManager();
        movieObservableList.setAll(movieManager.getAll());
    }

    public ObservableList<Movie> getMovieObservableList() {
        return movieObservableList;
    }

    public void addMovie(Movie movie) {
        movieObservableList.add(movie);
    }
}