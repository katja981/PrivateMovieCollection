package BE;

import java.time.LocalDate;

public class Movie {
    private int id;
    private String name; // Name of the movie.
    private String genre; // Movie genre.
    private float rating; // Rating of the movie.
    private String filelink; // Path to the movie in the resource folder.
    private LocalDate lastViewDate; // Date of the last view.

    // Constructor for the Movie class
    public Movie(int id, String name, String genre, String filelink) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.filelink = filelink;
        this.lastViewDate = LocalDate.now();
    }

    // Getters and setters for all variables
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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
        return name + ", " + genre + ", Last viewed: " + lastViewDate;
    }
}
