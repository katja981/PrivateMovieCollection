package dk.easv.privatemoviecollection;

import BE.Movie;
import DAL.DB;
import BE.Movie;
import DAL.MovieDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.util.List;

public class HelloController {

    @FXML
    private ListView<Movie> movieListView;

    private ObservableList<Movie> movieObservableList;

    @FXML
    public void initialize() {
        loadMovies();
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
}