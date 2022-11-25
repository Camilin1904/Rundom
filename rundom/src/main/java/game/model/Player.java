package game.model;

public class Player implements Moveable{
    //private static Player instance = new Player();
    private String dir;
    private String name;
    private double score;

    private Player right;
    private Player left;
    private Vertex<String, Moveable> position;



    public Player (String name, double score) {
        this.name=name;
        this.score=score;
    }

    public Player getRight() {
        return right;
    }

    public void setRight(Player right) {
        this.right = right;
    }

    public Player getLeft() {
        return left;
    }

    public void setLeft(Player left) {
        this.left = left;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
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
                    position.setValue(this);
                }
                break;
            case("L"):
                if((newPos = position.getLeft())!=null){
                    position.setValue(null);
                    position = newPos.getA();
                    position.setValue(this);
                }
                break;
            case("U"):
                if((newPos = position.getUp())!=null){
                    position.setValue(null);
                    position = newPos.getA();
                    position.setValue(this);
                }
                break;
            case("D"):
                if((newPos = position.getDown())!=null){
                    position.setValue(null);
                    position = newPos.getA();
                    position.setValue(this);
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
