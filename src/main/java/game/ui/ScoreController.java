package game.ui;

import game.model.Controller;
import game.model.Pair;
import game.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScoreController implements Initializable {

    private Controller controler= Rundom.ctrl;

    @FXML
    private Canvas canvas;

    @FXML
    private Label name1;

    @FXML
    private Label name10;

    @FXML
    private Label name2;

    @FXML
    private Label name3;

    @FXML
    private Label name4;

    @FXML
    private Label name5;

    @FXML
    private Label name6;

    @FXML
    private Label name7;

    @FXML
    private Label name8;

    @FXML
    private Label name9;

    @FXML
    private Button returnBTN;

    @FXML
    private Label score1;

    @FXML
    private Label score10;

    @FXML
    private Label score2;

    @FXML
    private Label score3;

    @FXML
    private Label score4;

    @FXML
    private Label score5;

    @FXML
    private Label score6;

    @FXML
    private Label score7;

    @FXML
    private Label score8;

    @FXML
    private Label score9;

    private GraphicsContext gc;
    private Image bg;

    @FXML
    void returnMain(ActionEvent event) {
            controler.setActual(new Player(score9.getText(), 0));
            //controler.createScenario(10, 1.6);
            Rundom.showWindow("primary.fxml");

            controler.insert(controler.getActual());
            controler.inorder();
            Stage current = (Stage) score9.getScene().getWindow();
            current.hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        drawBackground();
        updateSb();
    }

    public void drawBackground(){

        String uri2 = "file:"+ Rundom.class.getResource("leel.png").getPath();
        bg = new Image(uri2);
        gc.save();
        gc.drawImage(bg, 0,0, 773,549);
        gc.restore();

    }

    public void updateSb(){
        ArrayList<Pair<String, Double>> players = controler.updateLeaderboard();

        if(players.size()>=1 && players.get(0)!=null){
            name1.setText(players.get(0).getA());
            score1.setText(players.get(0).getB() + "");
        }
        if(players.size()>=2 && players.get(1)!=null){
            name2.setText(players.get(1).getA());
            score2.setText(players.get(1).getB() + "");
        }
        if(players.size()>=3 && players.get(2)!=null){
            name3.setText(players.get(2).getA());
            score3.setText(players.get(2).getB() + "");
        }
        if(players.size()>=4 && players.get(3)!=null){
            name4.setText(players.get(3).getA());
            score4.setText(players.get(3).getB() + "");
        }
        if(players.size()>=5 && players.get(4)!=null){
            name5.setText(players.get(4).getA());
            score5.setText(players.get(4).getB() + "");
        }
        if(players.size()>=6 && players.get(5)!=null){
            name6.setText(players.get(5).getA());
            score6.setText(players.get(5).getB() + "");
        }
        if(players.size()>=7 && players.get(6)!=null){
            name7.setText(players.get(6).getA());
            score7.setText(players.get(6).getB() + "");
        }
        if(players.size()>=8 && players.get(7)!=null){
            name8.setText(players.get(7).getA());
            score8.setText(players.get(7).getB() + "");
        }
        if(players.size()>=9 && players.get(8)!=null){
            name9.setText(players.get(8).getA());
            score9.setText(players.get(8).getB() + "");
        }
        if(players.size()>=10 && players.get(9)!=null){
            name10.setText(players.get(9).getA());
            score10.setText(players.get(9).getB() + "");
        }

    }
}

