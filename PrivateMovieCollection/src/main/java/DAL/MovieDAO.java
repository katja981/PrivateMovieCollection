package DAL;

import BE.Movie;
import exceptions.PlayerException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private DB con = new DB();

    public Movie add(Movie movie) throws PlayerException {
        String sql = "INSERT INTO Movie (movieName, imdbRating, filelink, lastview) VALUES (?, ?, ?, ?)";
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
            String sql = "DELETE FROM Movie WHERE id = ?";
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

        try (Connection c = con.DBConnection();) {
            String sql = "SELECT [id], [movieName], [imdbRating], [filelink], [lastview] FROM Movie";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String movieName = rs.getString("movieName");
                float imdbRating = rs.getFloat("imdbRating");
                String fileLink = rs.getString("filelink");
                Date lastView = rs.getDate("lastview");

                Movie movie = new Movie(id, movieName, imdbRating, "Genre", fileLink, lastView);
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public Movie updateMovie(Movie movie) throws SQLException {
        String sql = "UPDATE movie SET movieName = ?, imdbRating = ?, filelink = ?, lastview = ? WHERE id = ?";
        try (Connection c = con.DBConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, movie.getMovieName());
            stmt.setFloat(2, movie.getImdbRating());
            stmt.setString(3, movie.getFileLink());
            stmt.setDate(4, movie.getLastViewDate());
            stmt.setInt(5, movie.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
         throw new PlayerException("Error updating movie", e);
        }
        return movie;
    }
}