package BE;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int movieId;
    private String movieName; // Name of the movie.
    private Double personalRating; // Movie category.
    private float imdbRating; // IMDB rating of the movie.
    private String fileLink; // Path to the movie in the resource folder.
    private java.sql.Date lastViewDate; // Date of the last view.
    private List<String> categories; // List for categories.

    // Constructor for the Movie class
    public Movie(int movieId, String movieName, float imdbRating, Double personalRating, String fileLink, Date lastViewDate) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.imdbRating = imdbRating;
        this.personalRating = personalRating;
        this.fileLink = fileLink;
        this.lastViewDate = lastViewDate;
        this.categories = new ArrayList<>();
    }

    public Movie(int movieId, String movieName, List<String> categories) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.categories = categories;
    }

    // Getters and setters for all variables
    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }


    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Double getPersonalRating() {
        return personalRating;
    }

    public void setPersonalRating(Double personalRating) {
        this.personalRating = personalRating;
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(float imdbRating) {
        // Validate IMDB rating (must be between 0.0 and 10.0)
        if (imdbRating >= 0.0 && imdbRating <= 10.0) {
            this.imdbRating = imdbRating;
        } else {
            throw new IllegalArgumentException("IMDB rating must be between 0.0 and 10.0.");
        }
    }

    public java.sql.Date getLastViewDate() {
        return lastViewDate;
    }


    public void setLastViewDate(java.sql.Date lastViewDate) {
        this.lastViewDate = lastViewDate;
    }

    public String getFileLink() {
        return fileLink;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setFileLink(String fileLink) {
        // Check if the file ends with .mp4 or .mpeg4
        if (fileLink != null && (fileLink.endsWith(".mp4") || fileLink.endsWith(".mpeg4"))) {
            this.fileLink = fileLink;
        } else {
            throw new IllegalArgumentException("File must be in .mp4 or .mpeg4 format.");
        }
    }

    // Override the toString method to return a string representation of the Movie object
    @Override
    public String toString() {
        return "Movie: " + (movieName != null ? movieName : "Unknown") +
                ", Rating: " + imdbRating +
                ", Last Viewed: " + (lastViewDate != null ? lastViewDate : "Never");
    }

    public void setMovieId(int anInt) {
        movieId = anInt;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}