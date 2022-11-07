module game.aplication {
    requires javafx.controls;
    requires javafx.fxml;

    opens game.ui to javafx.fxml;
    exports game.ui;
}
