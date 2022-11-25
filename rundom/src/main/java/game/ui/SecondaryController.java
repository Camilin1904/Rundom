package game.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SecondaryController implements Initializable {

    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;
    private boolean isRunning = true;
    private Image bg;


    //Elementos gráficos
    private Avatar avatar;


    //Estados de las teclas
    boolean Wpressed = false;
    boolean Apressed = false;
    boolean Spressed = false;
    boolean Dpressed = false;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        canvas.setOnKeyPressed(this::onKeyPressed);

        avatar = new Avatar(canvas);
        draw();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
        if(keyEvent.getCode() == KeyCode.W){
            avatar.moveUp();
        }
        if(keyEvent.getCode() == KeyCode.A){
           avatar.moveLeft();
        }
        if(keyEvent.getCode() == KeyCode.S){
            avatar.moveDown();
        }
        if(keyEvent.getCode() == KeyCode.D){
           avatar.moveRight();
        }
    }

    public void draw(){
        new Thread(
                ()->{
                    while(isRunning){
                        //Dibujo
                        Platform.runLater(()->{


                            avatar.draw();
                            drawBackground();
                            avatar.draw();
                        });
                        //Sleep
                        try {
                            Thread.sleep(17);
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

}