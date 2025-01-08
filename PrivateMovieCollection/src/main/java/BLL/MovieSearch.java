package BLL;

import java.sql.Time;
import java.time.LocalDate;

public class MovieSearch {

    public class movie;
        private int id;
        private String name; // Name of the movie.
        private String genre; // Movie genre of the track.
        private float rating; // the value  of the movie in seconds.
        private String filelink; // Path to the movie in the recourse folder.
        private LocalDate lastViewDate;


        // Constructor for our movie
        public Movie (int id, String name, String genre,  String filelink) {
            this.id = id;
            this.name = name;
            this.genre = genre;
            this.filelink = filelink;
            this.lastViewDate = LocalDate.now();
        }

        //Getters and setters for all our variables.
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

    get() {
            lastViewDate date;
            return lastViewDate;
        }
        public void setLastViewDate(Time timer){this.lastViewDate = timer;}

        public String getFilelink() {
            return filelink;
        }

        public void setFilelink(String Filelink) {
            //check if the file ends with .mp4 or mpeg4
            if (filelink.endsWith(".mp4") || filelink.endsWith(".mpeg4")) {
                this.filelink = filelink;
            }
        }
        @Override
        public String toString(){return name + ",  " + genre + ", " + lastViewDate;}
    }

