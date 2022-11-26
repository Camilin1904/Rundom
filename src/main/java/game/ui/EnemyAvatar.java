package game.ui;

import game.model.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

@SuppressWarnings("unchecked")
public class EnemyAvatar extends Avatar{

    public EnemyAvatar(Canvas canvas, String image) {
        super(canvas, image);
    }
    private GraphicsContext gc;

    private Image tank, bg;
    private int dir = 1;
    
    @Override
    public void draw() {
        String coord = ((Vertex<String,Moveable>) characterInside.getPosition()).toString();
        pos.y = Integer.parseInt(coord.charAt(0) + "")*70;
        pos.x = Integer.parseInt(coord.charAt(2) + "")*70;
        super.draw();
    }
    public void move() {

        gc.save();

        if (dir == 0) {
            gc.drawImage(new Image("file:" + Rundom.class.getResource("evilback.png").getPath()), pos.x, pos.y, 70, 70);
        } else if (dir == 1) {
            gc.drawImage(new Image("file:" + Rundom.class.getResource("evilfront.png").getPath()), pos.x, pos.y, 70, 70);
        } else if (dir == 2) {
            gc.drawImage(new Image("file:" + Rundom.class.getResource("evilleft.png").getPath()), pos.x, pos.y, 70, 70);
        } else {
            gc.drawImage(new Image("file:" + Rundom.class.getResource("evilrightpng.png").getPath()), pos.x, pos.y, 70, 70);
        }
        gc.restore();
    }

    /*@Override
    public void move(){

    }*/
}
