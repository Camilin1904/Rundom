package game.model;

import java.util.*;

public class MatrixGraph<I extends Comparable<I>, T> implements DirectedGraph<I,T>, Iterable<Vertex<I, T>>{

    private HashMap<I, HashMap<I, Integer>> adyacenseMatrix;
    private HashMap<I,Vertex<I,T>> vertexCollection;

    public MatrixGraph(){
        adyacenseMatrix = new HashMap<>();
        vertexCollection = new HashMap<>();
        
    }

    @Override
    public void addVertex(I id, T toAdd) {
        adyacenseMatrix.put(id, new HashMap<>());
        for(Map.Entry<I, Vertex<I, T>> item : vertexCollection.entrySet()){
            adyacenseMatrix.get(id).put(item.getKey(), 0);
        }
        
        for (Map.Entry<I,HashMap<I,Integer>> item : adyacenseMatrix.entrySet()){
            item.getValue().put(id, 0);
        }

        vertexCollection.put(id, new Vertex<I, T>(id, toAdd));
        
    }

    @Override
    public void addConnection(I pointer, I pointed, String direction, int weight) {
        adyacenseMatrix.get(pointer).replace(pointed, 0, weight);
        
    }

    @Override
    public T search(I id) {
        return vertexCollection.get(id).getValue();
    }

    public Vertex<I, T> searchVertex(I id){
        return vertexCollection.get(id);
    }

    @Override
    public void BFS(I s) {
        Vertex<I,T> h  =searchVertex(s);
        ArrayList<Vertex<I, T>> queue = new ArrayList<>();
        for(Vertex<I, T> n : this){
            n.setColor(0);
            n.setDistance(Integer.MAX_VALUE);
            n.setParent(null);
        }
        h.setColor(1);
        h.setDistance(0);
        h.setParent(null);
        queue.add(h);
        while(!queue.isEmpty()){
            Vertex<I, T> u = queue.remove(0);
            for(Map.Entry<I,Integer> n : adyacenseMatrix.get(u.getId()).entrySet()){
                if(n.getValue()>0){
                    if(searchVertex(n.getKey()).getColor()==0){
                        searchVertex(n.getKey()).setColor(1);
                        searchVertex(n.getKey()).setDistance(u.getDistance()+1);
                        searchVertex(n.getKey()).setParent(u);
                        queue.add(searchVertex(n.getKey()));
                    }
                }
            }
            u.setColor(2);
        }
        
    }

    @Override
    public void DFS() {
        for(Vertex<I, T> n : this){
            n.setColor(0);
            n.setParent(null);
        }
        int time = 0;
        for (Vertex<I, T> n : this){
            if(n.getColor()==0) time = DFSVisit(n, time);
        }
    }

    private int DFSVisit(Vertex<I, T> vertex, int time){ 
        System.out.println(vertex.getValue().getClass());
        time++;
        vertex.setInitial(time);
        vertex.setColor(1);
        for (Map.Entry<I,Integer> n : adyacenseMatrix.get(vertex.getId()).entrySet()){
            if(n.getValue()>0){
                if(searchVertex(n.getKey()).getColor()==0){
                    searchVertex(n.getKey()).setParent(vertex);
                    DFSVisit(searchVertex(n.getKey()), time);
                }
            }
        }
        vertex.setColor(2);
        time++;
        vertex.setEnd(time);
        return time;
    }

    @Override
    public Stack<?> dijktraPath(I startID, I endID) {
        Vertex<I,T> start = searchVertex(startID);
        Vertex<I,T> end = searchVertex(endID);
        ArrayList<Vertex<I, T>> vertexes = new ArrayList<>();
        Comparator<Vertex<I, T>> comp = new Comparator<Vertex<I, T>>() {
            @Override
            public int compare(Vertex<I, T> o1, Vertex<I, T> o2) {
                return o1.getDistance()-o2.getDistance();
            }
        };

        start.setDistance(0);
        for(Vertex<I, T> item : this){
            if(item!=start) item.setDistance(Integer.MAX_VALUE);
            item.setParent(null);
            vertexes.add(item);
        }

        vertexes.sort(comp);
        Stack<Vertex<I, T>> path = new Stack<>();
        System.out.println(vertexes);
        Vertex<I, T> u = null;
        while(!vertexes.isEmpty()&&u!=end){
            u = vertexes.remove(0);
            for (Map.Entry<I,Integer> n : adyacenseMatrix.get(u.getId()).entrySet()){
                if(n.getValue()>0){
                    int dist = n.getValue() + u.getDistance();
                    if(dist<searchVertex(n.getKey()).getDistance()){
                        searchVertex(n.getKey()).setDistance(dist);
                        searchVertex(n.getKey()).setParent(u);
                    }
                }
            }
            System.out.println("-" + u);
            System.out.println("+" + start + "/" + end);
            vertexes.sort(comp);
        }

        while(u!=start){
            path.push(u);
            u = u.getParent();
        }
        return path;
    }

    @Override
    public boolean checkConexivity(I start) {
        BFS(start);
        boolean conexed = true;
        for(Vertex<I,T> item : this){
            if(item.getType()!=0){ 
                if(conexed = (item.getColor()!=2)) break;
            }
        }

        return conexed;
    }

    @Override
    public Iterator<Vertex<I, T>> iterator() {
        ArrayList<Vertex<I, T>> things = new ArrayList<>();
        for(Map.Entry<I, Vertex<I, T>> i : vertexCollection.entrySet()) things.add(i.getValue());
        return things.iterator();
    }
    
}
