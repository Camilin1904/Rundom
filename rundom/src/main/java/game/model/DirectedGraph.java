package game.model;

public interface DirectedGraph<I, T>{

    public void addVertex(I id, T toAdd);

    public void addConnection(I pointer, I pointed, String direction, int weight);


}