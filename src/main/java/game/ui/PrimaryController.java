package game.ui;

import game.model.Controller;
import game.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    private Controller ctrl= Rundom.ctrl;
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
    private Image wall;
    @FXML
    private Canvas canvas;
    @FXML
    private ToggleButton modeSwitch;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        if(ctrl!=null&&ctrl.getActual()!=null){
            ctrl.getActual().setScore(ctrl.getActual().getScore() + ctrl.getActual().getPosScore().getFloor()*200 + ctrl.getActual().getPosScore().getRoom()*10);
            ctrl.getActual().clean();
            ctrl.insert(ctrl.getActual());
        }
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
        Rundom.ctrl = new Controller(modeSwitch.isSelected());
        ctrl = Rundom.ctrl;
        ctrl.setActual(new Player(playerNickname.getText(), 0));
        //ctrl.createScenario(10, 1.6);
        Rundom.showWindow("canvasView.fxml");
        Stage current = (Stage) playerNickname.getScene().getWindow();
        current.hide();
    }

    @FXML
    void openLb(ActionEvent event) {
        ctrl.setActual(new Player(playerNickname.getText(), 0));
        //ctrl.createScenario(10, 1.6);
        Rundom.showWindow("scores.fxml");

        Stage current = (Stage) playerNickname.getScene().getWindow();
        current.hide();
    }
}