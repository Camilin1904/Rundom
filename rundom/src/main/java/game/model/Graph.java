package game.model;

import java.util.*;

public class Graph<T> implements DirectedGraph<T>{

    private HashMap<T,Vertex<T>> vertexCollection;

    public Graph(){
        vertexCollection = new HashMap<>();
    }

    @Override
    public void addVertex(T toAdd) {
        vertexCollection.put(toAdd, new Vertex<T>(toAdd));
    }

    @Override
    public void addConnection(T pointer, T pointed, String direction) {
        switch (direction){
            case ("R"):
                vertexCollection.get(pointer).setRight(vertexCollection.get(pointed));
                break;
            case("L"):
                vertexCollection.get(pointer).setLeft(vertexCollection.get(pointed));
                break;
            case("U"):
                vertexCollection.get(pointer).setUp(vertexCollection.get(pointed));
                break;
            case("D"):
                vertexCollection.get(pointer).setDown(vertexCollection.get(pointed));
                break;
            default:
                System.out.println("No");
        }
        
    }
    
}
