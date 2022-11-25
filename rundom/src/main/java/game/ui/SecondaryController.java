package game.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;

import game.model.*;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

import game.model.Enemy;

public class SecondaryController implements Initializable {

    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;
    private boolean isRunning = true;
    private Image bg;
    private Image wall;

    private int[][] template;


    //Elementos gráficos
    private Avatar avatar;


    //Estados de las teclas
    boolean Wpressed = false;
    boolean Apressed = false;
    boolean Spressed = false;
    boolean Dpressed = false;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        template = Rundom.ctrl.createScenario(10, 1.6);
        Rundom.ctrl.generateKeyPositions(3);
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        avatar = new Avatar(canvas);
        avatar.setCharacterInside(Rundom.ctrl.getActual());
        canvas.setOnKeyPressed(this::onKeyPressed);

        draw();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
        if(keyEvent.getCode() == KeyCode.W){
            if(avatar.move("U")!=null) avatar.moveUp();
            
        }
        if(keyEvent.getCode() == KeyCode.A){
           if(avatar.move("L")!=null)avatar.moveLeft();
           
        }
        if(keyEvent.getCode() == KeyCode.S){
            if(avatar.move("D")!=null) avatar.moveDown();
            
        }
        if(keyEvent.getCode() == KeyCode.D){
           if(avatar.move("R")!=null)avatar.moveRight();
           
        }
        Enemy.getInstance().updatePath();
        System.out.println(((Player)avatar.getCharacterInside()).getPosition());
    }

    public void draw(){
        new Thread(
                ()->{
                    while(isRunning){
                        //Dibujo
                        Platform.runLater(()->{


                            drawBackground();
                            avatar.draw();
                            drawWalls();
                        });
                        //Sleep
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();
    }

    public void drawBackground(){

        String uri2 = "file:"+ Rundom.class.getResource("suelo.png").getPath();
        bg = new Image(uri2);
        gc.save();
        gc.drawImage(bg, 0,0, 700,700);
        gc.restore();

    }
    
    public void drawWalls(){
        new Thread(()->{
            for(int i = 0; i<10; i++){
                for(int e=0; e<10; e++){
                    if(template[i][e]==0){
                        String uri3 = "file:"+ Rundom.class.getResource("cc.png").getPath();
                        wall = new Image(uri3);
                        gc.save();
                        gc.drawImage(wall, e, i, 70, 70);
                        gc.restore();
                    }
                }
            }
        });
    }

}