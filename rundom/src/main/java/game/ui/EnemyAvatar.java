package game.ui;

import game.model.*;
import javafx.scene.canvas.Canvas;

@SuppressWarnings("unchecked")
public class EnemyAvatar extends Avatar{

    public EnemyAvatar(Canvas canvas, String image) {
        super(canvas, image);
    }
    
    @Override
    public void draw() {
        String coord = ((Vertex<String,Moveable>) characterInside.getPosition()).toString();
        pos.y = Integer.parseInt(coord.charAt(0) + "")*70;
        pos.x = Integer.parseInt(coord.charAt(2) + "")*70;
        super.draw();
    }

    /*@Override
    public void move(){

    }*/
}
