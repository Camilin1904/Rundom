package game.model;

import java.util.*;

public class Graph<I, T> implements DirectedGraph<I, T>, Iterable<Vertex<I, T>>{

    private HashMap<I, Vertex<I, T>> vertexCollection;
    private HashMap<T, Vertex<I, T>> aux;

    public Graph(){
        vertexCollection = new HashMap<>();
        aux = new HashMap<>();
    }

    @Override
    public void addVertex(I id, T toAdd) {
        Vertex<I, T> t = new Vertex<I, T>(id, toAdd);
        vertexCollection.put(id, t);
        aux.put(toAdd, t);
    }

    @Override
    public void addConnection(I pointer, I pointed, String direction, int weight) {
        
        Pair<Vertex<I, T>,Integer> p = new Pair<Vertex<I, T>,Integer>(vertexCollection.get(pointed), weight);
        
        switch (direction){
            case ("R"):
                vertexCollection.get(pointer).setRight(p);
                break;
            case("L"):
                vertexCollection.get(pointer).setLeft(p);
                break;
            case("U"):
                vertexCollection.get(pointer).setUp(p);
                break;
            case("D"):
                vertexCollection.get(pointer).setDown(p);
                break;
            default:
                System.out.println("No");
        }
        
    }

    @Override
    public Iterator<Vertex<I, T>> iterator() {
        ArrayList<Vertex<I, T>> things = new ArrayList<>();
        for(Map.Entry<I, Vertex<I, T>> i : vertexCollection.entrySet()) things.add(i.getValue());
        return things.iterator();
    }

    public Vertex<I, T> searchVertex(I id){
        return vertexCollection.get(id);
    }

    public Vertex<I, T> containerOf(T value){
        return aux.get(value);
    }
    
}