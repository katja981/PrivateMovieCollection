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
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/privatemoviecollection/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();

        // Create list of movies with their file paths
        ObservableList<String> movies = FXCollections.observableArrayList(
                "C:/Users/katja/Downloads/COWS_AT_THE_GRASS.mp4",
                "C:/Users/katja/Downloads/movie2.mp4",
                "C:/Users/katja/Downloads/movie3.mp4",
                "C:/Users/katja/Downloads/movie4.mp4"
        );

        // Lookup the ListView from FXML
        ListView<String> movieListView = (ListView<String>) scene.lookup("#movieListView");
        if (movieListView != null) {
            // Set the list of movies to the ListView
            movieListView.setItems(movies);

            // Set double-click event on the ListView
            movieListView.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    String selectedItem = movieListView.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        System.out.println("Opening movie: " + selectedItem);
                        openMovie(selectedItem);
                    }
                }
            });
        } else {
            System.out.println("Movie ListView not found.");
        }
    }

    /**
     Method to open a movie file in default media player*/
    private void openMovie(String moviePath) {
        try {// Convert URL-style path to a valid system path
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