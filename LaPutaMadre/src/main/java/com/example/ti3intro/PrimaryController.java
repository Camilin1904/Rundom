package com.example.ti3intro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField playerNickname;

    @FXML
    private Button primaryButton;

    @FXML
    private GraphicsContext gc;
    @FXML
    private Image bg;
    @FXML
    private Canvas canvas;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        drawBackground();
    }

    public void drawBackground(){

        String uri2 = "file:"+ Rundom.class.getResource("leel.png").getPath();
        bg = new Image(uri2);
        gc.save();
        gc.drawImage(bg, 0,0, 773,549);
        gc.restore();

    }

    @FXML
    void press(ActionEvent event) {
        Rundom.showWindow("canvasView.fxml");
        Stage current = (Stage) playerNickname.getScene().getWindow();
        current.hide();
    }
}