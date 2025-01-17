package DAL;

import BE.Movie;
import exceptions.PlayerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private DB con = new DB();

    public Movie add(Movie movie) throws PlayerException {
        String sql = "INSERT INTO Movie (movieID, movieName, imdbRating, personalRating, filelink, lastview) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection c = con.DBConnection();
             PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, movie.getMovieId());
            stmt.setString(2, movie.getMovieName());
            stmt.setDouble(3, movie.getImdbRating());
            stmt.setDouble(4, movie.getPersonalRating());
            stmt.setString(5, movie.getFileLink());
            stmt.setDate(6, movie.getLastViewDate());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    movie.setMovieId(generatedKeys.getInt(1));
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
            String sql = "DELETE FROM Movie WHERE movieId = ?";
            System.out.println(movie.getMovieId());
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, movie.getMovieId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movie;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();

        try (Connection c = con.DBConnection();) {
            String sql = "SELECT [movieId], [movieName], [imdbRating], [personalRating], [filelink], [lastview] FROM Movie";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int movieId = rs.getInt("movieId");
                String movieName = rs.getString("movieName");
                float imdbRating = rs.getFloat("imdbRating");
                float personalRating = rs.getFloat("personalRating");
                String fileLink = rs.getString("filelink");
                Date lastView = rs.getDate("lastview");

                Movie movie = new Movie(movieId, movieName, imdbRating, personalRating, fileLink, lastView);
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
            stmt.setInt(5, movie.getMovieId());
            stmt.executeUpdate();
        } catch (SQLException e) {
         throw new PlayerException("Error updating movie", e);
        }
        return movie;
    }

    public boolean deleteMovie(Movie movie) {
        return false;
    }
}