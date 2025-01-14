package BLL;

import BE.Movie;
import DAL.MovieDAO;

import java.util.List;

public class MovieManager {
    private MovieDAO movieDAO;

    public MovieManager() {
        movieDAO = new MovieDAO();
    }

    public List<Movie> getAll() {
        try {
            return movieDAO.getAllMovies();
        } catch (Exception e) {
            System.err.println("Error fetching movies: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}