package game.model;

import java.util.Stack;

public class Enemy implements Moveable{

    private Vertex<String, Moveable> position;

    private Graph<String, Moveable> map;
    
    private Vertex<String, Moveable> goal = null;

    private Stack<Vertex<String, Moveable>> path = null;

    private static Enemy instance = new Enemy();

    private Enemy(){}
    
    public int move(){
        updatePath();
        
        position.setValue(null);
        position = path.pop();
        position.setValue(instance);
        return goal.getDistance();
    }

    public void updatePath(){
        Vertex<String, Moveable> pPos = map.containerOf(Player.getInstance());
        if(goal!=pPos){
            goal = pPos;
            path = GraphOperations.dijktraPath(map, position, pPos);
        }
        System.out.println(path);
    }

    public void setMap(Graph<String, Moveable> map) {
        this.map = map;
    }
    public void setPosition(Vertex<String, Moveable> position) {
        this.position = position;
    }
    public static Enemy getInstance() {
        return instance;
    }
}
