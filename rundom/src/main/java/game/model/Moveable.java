package game.model;

public interface Moveable {
    public String move();
    public String move(String dir);
    public Object getPosition();
}
