package BLL;

import BE.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieSearch {
    private final List<Movie> allMovies;

    public MovieSearch(List<Movie> allMovies) {
        this.allMovies = allMovies;
    }

    public List<Movie> searchMovies(String searchTerm) {
        if (searchTerm == null || searchTerm.isBlank()) {
            return allMovies; // Return all movies if search term is empty
        }

        return allMovies.stream()
                .filter(movie -> movie.getMovieName().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }
}