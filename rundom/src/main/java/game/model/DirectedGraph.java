package game.model;

import java.util.Stack;

public interface DirectedGraph<I, T>{

    public void addVertex(I id, T toAdd);

    public void addConnection(I pointer, I pointed, String direction, int weight);

    public T search(I id);

    public void BFS(I s);

    public void DFS();

    public Stack<?> dijktraPath(I startID, I endID);

    public boolean checkConexivity(I start);
}