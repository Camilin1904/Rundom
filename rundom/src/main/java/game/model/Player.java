package game.model;

public class Player implements Moveable{
    private static Player instance = new Player();
    private String dir;
    private Vertex<String, Moveable> position;

    private Player(){}

    public static Player getInstance() {
        return instance;
    }

    public int move(String dir){
        this.dir = dir;
        return move();
    }

    @Override
    public int move() {
        Pair<Vertex<String, Moveable>,Integer> newPos = null;
        switch(dir){
            case ("R"):
                newPos = position.getRight();
                break;
            case("L"):
                newPos = position.getLeft();
                break;
            case("U"):
                newPos = position.getUp();
                break;
            case("D"):
                newPos = position.getDown();
                break;

        }
        if(newPos!=null){
            position.setValue(null);
            position = newPos.getA();
            position.setValue(instance);
        }
        return newPos!=null?newPos.getB():-1;
    }
    public Vertex<String, Moveable> getPosition() {
        return position;
    }
    public void setPosition(Vertex<String, Moveable> position) {
        this.position = position;
    }
}
