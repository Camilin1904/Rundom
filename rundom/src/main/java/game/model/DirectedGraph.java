package game.model;

import java.util.*;

public interface DirectedGraph<I, T>{

    public void addVertex(I id, T toAdd);

    public void addConnection(I pointer, I pointed, String direction, int weight);

    public void addValue(I id, T value);

    public T search(I id);

    public void BFS(I s);

    public void DFS();

    public Stack<?> dijktraPath(I startID, I endID);

    public boolean checkConexivity(I start);

    public HashMap<I, HashMap<I,Integer>> floydWarshall();

    public int prim(I rs);

    public TreeSet<?> Kruskal();

    public Object containerOf(T value);
}