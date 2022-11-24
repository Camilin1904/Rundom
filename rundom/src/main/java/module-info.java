module game.aplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    

    opens game.ui to javafx.fxml;
    exports game.ui;
}
