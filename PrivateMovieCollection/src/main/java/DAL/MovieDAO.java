package DAL;

import BE.Movie;
import exceptions.PlayerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class MovieDAO {
    private DB con = new DB();

    public Movie add(Movie movie) throws PlayerException {
        String sql = "INSERT INTO Movie (movieName, imdbRating, personalRating, filelink, lastview) VALUES (?, ?, ?, ?, ?)";

        try (Connection c = con.DBConnection();
             PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, movie.getMovieName());
            stmt.setDouble(2, movie.getImdbRating());

            if (movie.getPersonalRating() == null) {
                stmt.setNull(3, Types.DOUBLE);
            } else {
                stmt.setDouble(3, movie.getPersonalRating());
            }

            stmt.setString(4, movie.getFileLink());

            if (movie.getLastViewDate() == null) {
                stmt.setNull(5, Types.DATE);
            } else {
                stmt.setDate(5, movie.getLastViewDate());
            }

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

    public ObservableList<Movie> getAllMovies() {
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        String sql = "SELECT movieId, movieName, imdbRating, personalRating, filelink, lastview FROM Movie";

        try (Connection c = con.DBConnection();
             PreparedStatement stmt = c.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int movieId = rs.getInt("movieId");
                String movieName = rs.getString("movieName");
                float imdbRating = rs.getFloat("imdbRating");

                Double personalRating = rs.getObject("personalRating") != null ? rs.getDouble("personalRating") : null;

                String fileLink = rs.getString("filelink");

                Date lastView = rs.getObject("lastview") != null ? rs.getDate("lastview") : null;

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

    public List<Movie> getMoviesWithCategories() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT m.movieId, m.movieName, c.categoryName " +
                "FROM Movie m " +
                "LEFT JOIN movieCategory mc ON m.movieId = mc.movie_id " +
                "LEFT JOIN Category c ON mc.category_id = c.categoryId";

        try (Connection c = con.DBConnection();
             PreparedStatement stmt = c.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            Map<Integer, Movie> movieMap = new HashMap<>();

            while (rs.next()) {
                int movieId = rs.getInt("movieId");
                String movieName = rs.getString("movieName");
                String category = rs.getString("categoryName");

                movieMap.putIfAbsent(movieId, new Movie(movieId, movieName, new ArrayList<>()));

                if (category != null && !movieMap.get(movieId).getCategories().contains(category)) {
                    movieMap.get(movieId).getCategories().add(category);
                }
            }

            movies.addAll(movieMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }


    private int getCategoryIdByName(String categoryName, Connection c) throws SQLException {
        String sql = "SELECT categoryId FROM Category WHERE categoryName = ?";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, categoryName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("categoryId");
                } else {
                    throw new SQLException("Category not found: " + categoryName);
                }
            }
        }
    }
}