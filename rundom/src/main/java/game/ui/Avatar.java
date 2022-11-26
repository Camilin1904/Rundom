package game.ui;

import game.model.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;

@SuppressWarnings("unchecked")
public class Avatar {

    private Canvas canvas;
    private GraphicsContext gc;

    private Image tank, bg;

    protected Vector pos;

    protected Moveable characterInside;


    public Avatar(Canvas canvas, String image){
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        String uri = "file:"+ Rundom.class.getResource(image).getPath();
        tank = new Image(uri);
        pos = new Vector(200,200);
    }

    public void draw(){
        gc.save();
        gc.drawImage(tank, pos.x,pos.y, 70,70);
        gc.restore();
    }

    public void setPosition(double x, double y) {
        pos.x = (int) x - 25;
        pos.y = (int) y - 25;
    }


    public void moveUp(){
            pos.y -= 70;
    }
    public void moveDown(){
            pos.y += 70;
    }
    public void moveLeft(){

            pos.x -= 70;
    }
    public void moveRight(){
            pos.x += 70;
    }
    public String move(String dir){
        return characterInside.move(dir);
    }

    public Vector getPos() {
        return pos;
    }

    public void setCharacterInside(Moveable characterInside) {
        this.characterInside = characterInside;
        String coord = ((Vertex<String,Moveable>) characterInside.getPosition()).toString();
        pos.y = Integer.parseInt(coord.charAt(0) + "")*70;
        pos.x = Integer.parseInt(coord.charAt(2) + "")*70;
    }

    public Moveable getCharacterInside() {
        return characterInside;
    }


}
