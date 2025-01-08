package BE;

import java.time.LocalDate;

public class Movie {
    private int id;
    private String moviename; // Name of the movie.
    private String genre; // Movie genre.
    private float rating; // Rating of the movie.
    private String filelink; // Path to the movie in the resource folder.
    private LocalDate lastViewDate; // Date of the last view.

    // Constructor for the Movie class
    public Movie(int id, String name, String genre, float rating, String filelink) {
        this.id = id;
        this.moviename = name;
        this.genre = genre;
        setRating(rating); // Validate and set the rating
        this.filelink = filelink;
        this.lastViewDate = LocalDate.now();
    }

    // Getters and setters for all variables
    public int getId() {
        return id;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setName(String moviename) {
        this.moviename = moviename;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        // Validate rating (must be between 0.0 and 5.0)
        if (rating >= 0.0 && rating <= 5.0) {
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("Rating must be between 0.0 and 5.0.");
        }
    }

    public LocalDate getLastViewDate() {
        return lastViewDate;
    }

    public void setLastViewDate(LocalDate lastViewDate) {
        this.lastViewDate = lastViewDate;
    }

    public String getFilelink() {
        return filelink;
    }

    public void setFilelink(String filelink) {
        // Check if the file ends with .mp4 or .mpeg4
        if (filelink.endsWith(".mp4") || filelink.endsWith(".mpeg4")) {
            this.filelink = filelink;
        } else {
            throw new IllegalArgumentException("File must be in .mp4 or .mpeg4 format.");
        }
    }

    @Override
    public String toString() {
        return "Movie: " + moviename +
                ", Genre: " + genre +
                ", Rating: " + rating +
                ", Last viewed: " + lastViewDate;
    }
}
