package game.model;

public interface DirectedGraph<T>{

    public void addVertex(T toAdd);

    public void addConnection(T pointer, T pointed, String direction);


}