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
                if((newPos = position.getRight())!=null){
                    position.setValue(null);
                    position = newPos.getA();
                    position.setValue(instance);
                }
                break;
            case("L"):
                if((newPos = position.getLeft())!=null){
                    position.setValue(null);
                    position = newPos.getA();
                    position.setValue(instance);
                }
                break;
            case("U"):
                if((newPos = position.getUp())!=null){
                    position.setValue(null);
                    position = newPos.getA();
                    position.setValue(instance);
                }
                break;
            case("D"):
                if((newPos = position.getDown())!=null){
                    position.setValue(null);
                    position = newPos.getA();
                    position.setValue(instance);
                }
                break;

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
