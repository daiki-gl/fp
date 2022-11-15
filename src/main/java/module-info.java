module com.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.finalproject to javafx.fxml;

    // opens com.finalproject.home to javafx.base;
    exports com.finalproject;
}
