package com.example.ti3intro.model;

import java.util.Stack;

@SuppressWarnings("unchecked")
public class Enemy implements Moveable{

    private Vertex<String, Moveable> position;

    private DirectedGraph<String, Moveable> map;
    
    private Vertex<String, Moveable> goal = null;

    private Stack<Vertex<String, Moveable>> path = null;

    private static Enemy instance = new Enemy();

    private Enemy(){
        path = new Stack<>();
    }
    
    public int move(){
        updatePath();
        
        position.setValue(null);
        position = path.pop();
        position.setValue(instance);
        return goal.getDistance();
    }

    public void updatePath(){
        path.clear();
        Vertex<String, Moveable> pPos = (Vertex<String, Moveable>) map.containerOf(Player.getInstance());
        if(goal!=pPos){
            goal = pPos;
            path = (Stack<Vertex<String, Moveable>>) map.dijktraPath(position.getId(), pPos.getId());
        }
        System.out.println(path);
    }

    public void setMap(DirectedGraph<String, Moveable> map) {
        this.map = map;
    }
    public void setPosition(Vertex<String, Moveable> position) {
        this.position = position;
    }
    public static Enemy getInstance() {
        return instance;
    }
}
