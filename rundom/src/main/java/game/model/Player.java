package game.model;

import java.util.ArrayList;

public class Player implements Moveable{
    private String dir;
    private String name;
    private ArrayList<String> names;
    private double score;

    private Player right;
    private Player left;
    private Vertex<String, Moveable> position;

    private Score posScore;



    public Player (String name, double score) {
        this.name=name;
        this.score=score;
        names = new ArrayList<String>();
        posScore = new Score();
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

    public String move(String dir){
        this.dir = dir;
        return move();
    }

    @Override
    public String move() {
        Pair<Vertex<String, Moveable>,Integer> newPos = null;
        String retrn;
        boolean check = false;
        switch(dir){
            case ("R"):
                if((newPos = position.getRight())!=null){
                    check = newPos.getA().getValue()!=null;
                    position.setValue(null);
                    position = newPos.getA();
                    position.setValue(this);
                }
                break;
            case("L"):
                if((newPos = position.getLeft())!=null){
                    check = newPos.getA().getValue()!=null;
                    position.setValue(null);
                    position = newPos.getA();
                    position.setValue(this);
                }
                break;
            case("U"):
                if((newPos = position.getUp())!=null){
                    check = newPos.getA().getValue()!=null;
                    position.setValue(null);
                    position = newPos.getA();
                    position.setValue(this);
                }
                break;
            case("D"):
                if((newPos = position.getDown())!=null){
                    check = newPos.getA().getValue()!=null;
                    position.setValue(null);
                    position = newPos.getA();
                    position.setValue(this);
                }
                break;

        }
        if(position.getHasKey())position.setHasKey(false);
        retrn = newPos!=null?newPos.getB().toString():null;
        if(check){
            retrn = "n";
        }
        return retrn;
    }

    public Vertex<String, Moveable> getPosition() {
        return position;
    }
    public void setPosition(Vertex<String, Moveable> position) {
        this.position = position;
    }

    public boolean empty() {
        return names.isEmpty();
    }

    public ArrayList<String> getNames(){
        return new ArrayList<>(names);
    }
    public void addArray(String value){
        names.add(value);
    }
    public Score getPosScore() {
        return posScore;
    }

    public void clean(){
        dir = null;
        position = null;
        posScore = null;
    }
}
