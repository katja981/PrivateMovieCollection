package BE;

import java.sql.Date;

public class Movie {
    private int movieId;
    private String movieName;
    private String movieCategory;// Name of the movie.
    private Float personalRating; // Movie category.
    private float imdbRating; // IMDB rating of the movie.
    private String fileLink; // Path to the movie in the resource folder.
    private java.sql.Date lastViewDate; // Date of the last view.

    // Constructor for the Movie class
    public Movie(int movieId, String name, float imdbRating, String movieCategory, float personalRating, String fileLink, Date lastViewDate) {
        this.movieId = movieId;
        this.movieName = name;
        this.imdbRating = imdbRating;
        this.movieCategory = movieCategory;
        this.personalRating = personalRating;
        this.fileLink = fileLink;
        this.lastViewDate = lastViewDate;
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

    public String getMovieCategory() {
        return movieCategory;
    }

    public double getPersonalRating() {
        return personalRating;
    }

    public void setPersonalRating(Float personalRating) {
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
                ", Category: " + (movieCategory != null ? movieCategory : "Unknown") +
                ", Rating: " + imdbRating +
                ", Last Viewed: " + (lastViewDate != null ? lastViewDate : "Never");
    }

    public void setMovieId(int anInt) {
        movieId = anInt;
    }
}