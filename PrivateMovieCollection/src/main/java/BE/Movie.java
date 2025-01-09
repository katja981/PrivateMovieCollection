package BE;

import java.time.LocalDate;

public class Movie {
    private int id;
    private String movieName; // Name of the movie.
    private String movieCategory; // Movie category.
    private float imdbRating; // IMDB rating of the movie.
    private String fileLink; // Path to the movie in the resource folder.
    private LocalDate lastViewDate; // Date of the last view.

    // Constructor for the Movie class
    public Movie(int id, String name, float imdbRating, String movieCategory, String fileLink) {
        this.id = id;
        this.movieName = name;
        setImdbRating(imdbRating); // Validate and set the IMDB rating
        this.movieCategory = movieCategory;
        this.fileLink = fileLink;
        this.lastViewDate = LocalDate.now();
    }

    // Getters and setters for all variables
    public int getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = this.movieName;
    }

    public String getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(String category) {
        this.movieCategory = movieCategory;
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

    public LocalDate getLastViewDate() {
        return lastViewDate;
    }

    public void setLastViewDate(LocalDate lastViewDate) {
        this.lastViewDate = lastViewDate;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        // Check if the file ends with .mp4 or .mpeg4
        if (fileLink.endsWith(".mp4") || fileLink.endsWith(".mpeg4")) {
            this.fileLink = fileLink;
        } else {
            throw new IllegalArgumentException("File must be in .mp4 or .mpeg4 format.");
        }
    }

    @Override
    public String toString() {
        return "Movie: " + movieName +
                ", Genre: " + movieCategory +
                ", Rating: " + imdbRating +
                ", Last viewed: " + lastViewDate;
    }

    public double getPersonalRating() {
        return 0;
    }

    public void setId(int anInt) {
        id = anInt;
    }
}