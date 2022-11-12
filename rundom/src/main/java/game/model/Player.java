package game.model;

public class Player implements Moveable{
    private static Player instance = new Player();

    private Player(){}

    public static Player getInstance() {
        return instance;
    }

    @Override
    public int move() {
        return 0;
    }
}
