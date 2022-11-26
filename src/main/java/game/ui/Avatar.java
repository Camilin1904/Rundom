package game.ui;

import game.model.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

@SuppressWarnings("unchecked")
public class Avatar {

    private Canvas canvas;
    private GraphicsContext gc;

    private Image tank, bg;
    private int dir = 1;

    protected Vector pos;

    protected Moveable characterInside;


    public Avatar(Canvas canvas, String image) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        String uri = "file:" + Rundom.class.getResource(image).getPath();
        tank = new Image(uri);
        pos = new Vector(200, 200);
    }

    public void draw() {
        gc.save();
        gc.drawImage(tank, pos.x, pos.y, 70, 70);
        gc.restore();
    }

    public void move() {

        gc.save();

        if (dir == 0) {
            gc.drawImage(new Image("file:" + Rundom.class.getResource("back.png").getPath()), pos.x, pos.y, 70, 70);
        } else if (dir == 1) {
            gc.drawImage(new Image("file:" + Rundom.class.getResource("front.png").getPath()), pos.x, pos.y, 70, 70);
        } else if (dir == 2) {
            gc.drawImage(new Image("file:" + Rundom.class.getResource("left.png").getPath()), pos.x, pos.y, 70, 70);
        } else {
            gc.drawImage(new Image("file:" + Rundom.class.getResource("right.png").getPath()), pos.x, pos.y, 70, 70);
        }
        gc.restore();
    }

    public void setPosition(double x, double y) {
        pos.x = (int) x - 25;
        pos.y = (int) y - 25;
    }


    public void moveUp() {
        dir = 0;
        pos.y -= 70;
    }

    public void moveDown() {
        dir = 1;
        pos.y += 70;
    }

    public void moveLeft() {
        dir = 2;
        pos.x -= 70;
    }

    public void moveRight() {
        dir = 4;
        pos.x += 70;
    }

    public String move(String dir) {
        return characterInside.move(dir);
    }

    public Vector getPos() {
        return pos;
    }

    public void setCharacterInside(Moveable characterInside) {
        this.characterInside = characterInside;
        String coord = ((Vertex<String, Moveable>) characterInside.getPosition()).toString();
        pos.y = Integer.parseInt(coord.charAt(0) + "") * 70;
        pos.x = Integer.parseInt(coord.charAt(2) + "") * 70;
    }

    public Moveable getCharacterInside() {
        return characterInside;
    }


}
