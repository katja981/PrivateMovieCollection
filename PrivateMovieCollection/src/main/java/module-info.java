module dk.easv.privatemoviecollection {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens dk.easv.privatemoviecollection to javafx.fxml;
    exports dk.easv.privatemoviecollection;
}