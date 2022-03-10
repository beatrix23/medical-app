module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example to javafx.fxml, org.junit.jupiter;
    exports org.example;

    opens org.example.domain to javafx.base;
    opens org.example.controllers to javafx.fxml;
}