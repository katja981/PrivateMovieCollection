package GUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    // A class to hold movie details
    static class Movie {
        String title;
        double rating;
        String lastViewedDate;
        String filePath;

        public Movie(String title, double rating, String lastViewedDate, String filePath) {
            this.title = title;
            this.rating = rating;
            this.lastViewedDate = lastViewedDate;
            this.filePath = filePath;
        }

        @Override
        public String toString() {
            return String.format("Movie: %s, Rating: %.1f, Last Viewed: %s", title, rating, lastViewedDate);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/privatemoviecollection/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();

        // Create a list of movies with their details
        ObservableList<Movie> movies = FXCollections.observableArrayList(
                new Movie("The Shawshank Redemption", 9.3, "2024-06-12",
                        "C:\\Users\\Pheas\\Downloads\\OneDrive_2025-01-16\\Stock mp4 videos\\y2mate.com - People Exercising Running Jogging Workout RoyaltyFree stock videos No copyright running_1080p.mp4"),
                new Movie("Spirited Away", 8.6, "2024-08-13",
                        "C:\\Users\\Pheas\\Downloads\\OneDrive_2025-01-16\\Stock mp4 videos\\y2mate.com - FROM EARTH TO SPACE  Free HD VIDEO  NO COPYRIGHT_720p.mp4"),
                new Movie("Saw", 7.6, "2022-01-12",
                        "C:\\Users\\Pheas\\Downloads\\OneDrive_2025-01-16\\Stock mp4 videos\\COWS_AT_THE_GRASS.mp4"),
                new Movie("Home Alone", 7.7, "2024-12-17",
                        "C:\\Users\\Pheas\\Downloads\\OneDrive_2025-01-16\\Stock mp4 videos\\tomp3.cc - Coding  Programming HD Stock Video  Free stock footage Free HD Videos  No Copyright  2021_1080p.mp4")
        );

        // Lookup the ListView from FXML
        ListView<Movie> movieListView = (ListView<Movie>) scene.lookup("#movieListView");
        if (movieListView != null) {
            // Set the list of movies to the ListView
            movieListView.setItems(movies);

            // Set double-click event on the ListView
            movieListView.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    Movie selectedMovie = movieListView.getSelectionModel().getSelectedItem();
                    if (selectedMovie != null) {
                        System.out.println("Opening movie: " + selectedMovie.filePath);
                        openMovie(selectedMovie.filePath);
                    }
                }
            });
        } else {
            System.out.println("Movie ListView not found.");
        }
    }

    /**
     * Method to open a movie file in default media player
     */
    private void openMovie(String moviePath) {
        try {
            // Convert URL-style path to a valid system path
            String filePath = moviePath.replace("file:///", "").replace("/", File.separator).replace("%20", " ");

            System.out.println("Converted file path: " + filePath);

            File movieFile = new File(filePath);
            if (movieFile.exists()) {
                // Open the movie file in the default media player
                Desktop.getDesktop().open(movieFile);
            } else {
                System.out.println("Movie file not found: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("Failed to open the movie file: " + moviePath);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}