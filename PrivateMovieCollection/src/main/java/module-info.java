module dk.easv.privatemoviecollection {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires java.naming;



    opens dk.easv.privatemoviecollection to javafx.fxml;
    exports dk.easv.privatemoviecollection;
}