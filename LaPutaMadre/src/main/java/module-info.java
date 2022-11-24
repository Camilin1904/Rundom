module com.example.ti3intro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.ti3intro to javafx.fxml;
    exports com.example.ti3intro;
}