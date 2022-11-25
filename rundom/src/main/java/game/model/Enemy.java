package game.model;

import java.util.Stack;

@SuppressWarnings("unchecked")
public class Enemy implements Moveable{
    private Player target;

    private Vertex<String, Moveable> position;

    private Graph<String, Moveable> map;
    
    private Vertex<String, Moveable> goal = null;

    private Stack<Vertex<String, Moveable>> path = null;

    private static Enemy instance = new Enemy();

    private Enemy(){
        path = new Stack<>();
    }
    
    public String move(){
        updatePath();
        
        position.setValue(null);
        String returnS = null;
        Vertex<String, Moveable> newPos = !path.isEmpty()?path.pop(): position;
        
        if(newPos.getValue()==null){
            String r = position.getRight().toString();
            String l = position.getLeft().toString();
            String u = position.getUp().toString();
            String d = position.getDown().toString();
            if(newPos.toString().equals(r)){
                    returnS = "R";
            }
            else if(newPos.toString().equals(l)){
                    returnS = "L";
            }
            else if(newPos.toString().equals(u)){
                returnS = "U";
            }
            else if(newPos.toString().equals(d)){
                    returnS = "D";
            }
    
            position = newPos;
        }
        
        return returnS;
    }

    public String move(String dir){
        return move();
    }

   public void updatePath(){
        path.clear();
        Vertex<String, Moveable> pPos = (Vertex<String, Moveable>) map.containerOf(target);
        if(goal!=pPos){
            goal = pPos;
            path = (Stack<Vertex<String, Moveable>>) map.dijktraPath(position.getId(), pPos.getId());
        }
        System.out.println(path);
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
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
    public Stack<Vertex<String, Moveable>> getPath() {
        return path;
    }
    public Vertex<String, Moveable> getPosition() {
        return position;
    }
}
