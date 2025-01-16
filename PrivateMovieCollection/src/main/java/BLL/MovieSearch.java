package BLL;

import BE.Movie;
import java.util.List;
import java.util.stream.Collectors;

public class MovieSearch {
    private final List<Movie> allMovies;

    public MovieSearch(List<Movie> allMovies) {
        this.allMovies = allMovies;
    }


    public List<Movie> filterMovies(String searchTerm, String category, double minRating) {
        System.out.println("Filtering with searchTerm: " + searchTerm + ", Category: " + category + ", Min Rating: " + minRating);

        return allMovies.stream()
                .filter(movie -> {
                    boolean matchesSearch = (searchTerm == null || searchTerm.isEmpty() ||
                            movie.getMovieName().toLowerCase().contains(searchTerm.toLowerCase()));

                    boolean matchesCategory = (category == null || movie.getMovieCategory().equalsIgnoreCase(category));

                    boolean matchesRating = (minRating <= 0 || movie.getImdbRating() >= minRating);

                    System.out.println("Movie: " + movie.getMovieName() + " - Matches search: " + matchesSearch +
                            ", Matches category: " + matchesCategory + ", Matches rating: " + matchesRating);
                    return matchesSearch && matchesCategory && matchesRating;
                })
                .collect(Collectors.toList());
    }
}