module game.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens game.ui to javafx.fxml;
    exports game.ui;
    exports game.model;
}
