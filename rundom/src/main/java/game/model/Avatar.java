package game.model;

import game.ui.Rundom;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;

public class Avatar {

    private Canvas canvas;
    private GraphicsContext gc;

    private Image tank, bg;

    private Vector pos;
    private Vector direction;
    private BackgroundImage bgImg;
    private int hBound = 0;
    private int vBound = 6;


    public Avatar(Canvas canvas){
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        String uri = "file:"+ Rundom.class.getResource("nessfuzzy.png").getPath();
        tank = new Image(uri);

        pos = new Vector(200,200);
    }

    public void draw(){
        gc.save();
        gc.translate(pos.x, pos.y);
        gc.drawImage(tank, -200,10, 70,70);
        gc.restore();
    }

    public void setPosition(double x, double y) {
        pos.x = (int) x - 25;
        pos.y = (int) y - 25;
    }


    public void moveUp(){
        if (vBound < 9) {
            pos.y -= 70;
            vBound++;
        }
    }
    public void moveDown(){
        if(vBound > 0){
            pos.y += 70;
            vBound--;
        }
    }
    public void moveLeft(){
        if(hBound > 0){
            pos.x -= 70;
            hBound--;
        }
    }
    public void moveRight(){
        if (hBound < 9){
            pos.x += 70;
            hBound++;
        }
    }

    public Vector getPos() {
        return pos;
    }


}
