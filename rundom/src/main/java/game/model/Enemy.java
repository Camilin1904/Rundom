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

    public Enemy(){
        path = new Stack<>();
    }
    
    public String move(){
        updatePath();
        
        position.setValue(null);
        String returnS = null;
        Vertex<String, Moveable> newPos = !path.isEmpty()?path.pop(): position;
        
        if(newPos!=null&&(newPos.getValue()==null||newPos.getValue()==target)){
            String r = position.getRight()!=null?position.getRight().toString():"";
            String l = position.getLeft()!=null?position.getLeft().toString():"";
            String u = position.getUp()!=null?position.getUp().toString():"";
            String d = position.getDown()!=null?position.getDown().toString():"";
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
            for(Pair<Vertex<String, Moveable>,Integer> i : position.getAdyacentVertex()){
                if(i.getA()==newPos){
                    if(i.getB()==2) returnS += "2";
                }
                break;
            }
            if (newPos.getValue()!=null&&newPos.getValue().equals(target)){
                returnS = "y";
            }
            position = newPos;
        }
        
        position.setValue(this);
        return returnS;
    }

    public String move(String dir){
        return move();
    }

   public int updatePath(){
        Vertex<String, Moveable> pPos = (Vertex<String, Moveable>) map.containerOf(target);
        if(goal!=pPos){
            path.clear();
            goal = pPos;
            path = (Stack<Vertex<String, Moveable>>) map.dijktraPath(position.getId(), pPos.getId());
        }
        return path.size();
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
