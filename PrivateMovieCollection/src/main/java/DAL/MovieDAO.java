package DAL;

import BE.Movie;
import exceptions.PlayerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private DB con = new DB();

    public Movie add(Movie movie) throws PlayerException {
        String sql = "INSERT INTO movies (title, genres, imdbRating, personalRating) VALUES (?, ?, ?, ?)";
        try (Connection c = con.DBConnection();
             PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, movie.getMovieName());
            stmt.setString(2, movie.getMovieCategory());
            stmt.setDouble(3, movie.getImdbRating());
            stmt.setDouble(4, movie.getPersonalRating());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    movie.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to retrieve generated ID.");
                }
            }
        } catch (SQLException e) {
            throw new PlayerException(e);
        }
        return movie;
    }

    public Movie delete(Movie movie) throws PlayerException {
        try {
            Connection c = con.DBConnection();
            String sql = "DELETE FROM movies WHERE id = ?";
            System.out.println(movie.getId());
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, movie.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movie;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try (Connection c = con.DBConnection()) {
            String sql = "SELECT * FROM movies";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String movieName = rs.getString("Name");
                float imdbRating = rs.getFloat("IMDB Rating");
                String fileLink = rs.getString("File link");
                String lastView = rs.getString("Last viewed");

                movies.add(new Movie(id, movieName, imdbRating, fileLink, lastView));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching movies", e);
        }
        return movies;
    }
}
