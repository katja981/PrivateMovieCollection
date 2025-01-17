package BLL;

import BE.Movie;
import DAL.MovieDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieManager {
    private static final Logger LOGGER = Logger.getLogger(MovieManager.class.getName());
    private static final ObservableList<Movie> sharedMovieList = FXCollections.observableArrayList();
    private final MovieDAO movieDAO;

    public boolean deleteMovie(Movie movie) {
        try {
            boolean deletedFromDB = movieDAO.deleteMovie(movie); // Assumes this method exists in MovieDAO
            if (deletedFromDB) {
                sharedMovieList.remove(movie); // Update observable list
            }
            return deletedFromDB;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting movie", e);
            return false;
        }
    }


    public MovieManager() {
        movieDAO = new MovieDAO();
        List<Movie> moviesFromDB = getAll();
        if (moviesFromDB != null) {
            sharedMovieList.addAll(moviesFromDB);
        }
    }

    public static ObservableList<Movie> getSharedMovieList() {
        return sharedMovieList;
    }

    public List<Movie> getAll() {
        try {
            return movieDAO.getAllMovies();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error fetching movies", e);
            return null;
        }
    }
}

