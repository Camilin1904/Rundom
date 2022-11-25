package game.model;

import java.util.*;

public class MatrixGraph<I extends Comparable<I>, T> implements Graph<I,T>, Iterable<Vertex<I, T>>{

    private HashMap<I, HashMap<I, Integer>> adyacenseMatrix;
    private HashMap<I,Vertex<I,T>> vertexCollection;
    private HashMap<T, Vertex<I, T>> aux;

    public MatrixGraph(){
        adyacenseMatrix = new HashMap<>();
        vertexCollection = new HashMap<>();
        aux = new HashMap<>();
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
        Vertex<I,T> t = new Vertex<I, T>(id, toAdd);
        vertexCollection.put(id, t);
        if(toAdd!=null) aux.put(toAdd, t);
        
    }

    public void addConnection(I pointer, I pointed, String direction, int weight) {
        adyacenseMatrix.get(pointer).replace(pointed, weight);
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
    public void addConnection(I pointer, I pointed, int weight) {
        adyacenseMatrix.get(pointer).replace(pointed, weight);
        System.out.println("/");
        for (Map.Entry<I,HashMap<I,Integer>> i : adyacenseMatrix.entrySet()){
            System.out.println(i.getValue());
        }
    }

    @Override
    public void addValue(I id, T value){
        searchVertex(id).setValue(value);
        aux.put(value, searchVertex(id));
    }

    @Override
    public T search(I id) {
        return vertexCollection.get(id).getValue();
    }

    public Vertex<I, T> searchVertex(I id){
        return vertexCollection.get(id);
    }

    @Override
    public Vertex<I, T> containerOf(T value){
        return aux.get(value);
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
    public Stack<Vertex<I,T>> dijktraPath(I startID, I endID) {
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
                if(item.getColor()!=2){
                    conexed = false;
                    break;
                }
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

    public HashMap<I, HashMap<I,Integer>> floydWarshall(){
        HashMap<I, HashMap<I,Integer>> dist = new HashMap<>();
        for (Map.Entry<I,Vertex<I,T>> i : vertexCollection.entrySet()){
            dist.put(i.getKey(), new HashMap<>());
        }
        for (Map.Entry<I,Vertex<I,T>> i : vertexCollection.entrySet()){
            for(Map.Entry<I,HashMap<I,Integer>> j : dist.entrySet()){
                j.getValue().put(i.getKey(), Integer.MAX_VALUE);
            }
        }
        for (Map.Entry<I,Vertex<I,T>> i : vertexCollection.entrySet()){
            dist.get(i.getKey()).replace(i.getKey(), 0);
        }
        for (Vertex<I,T> item : this){
            for (Map.Entry<I,Integer> i : adyacenseMatrix.get(item.getId()).entrySet()){
                if(i.getValue()>0) dist.get(item.getId()).replace(i.getKey(), i.getValue());
            }
        }

        for (Map.Entry<I,Vertex<I,T>> k : vertexCollection.entrySet()){
            for (Map.Entry<I,Vertex<I,T>> i : vertexCollection.entrySet()){
                for (Map.Entry<I,Vertex<I,T>> j : vertexCollection.entrySet()){
                    long o = 0, a = 0, b = 0;
                    a = (dist.get(i.getKey()).get(k.getKey()));
                    b = (dist.get(k.getKey()).get(j.getKey()));
                    if(dist.get(i.getKey()).get(j.getKey())>(o = a +b <Integer.MAX_VALUE?a+b:Integer.MAX_VALUE)&&i!=j){
                        dist.get(i.getKey()).replace(j.getKey(), (int) o);
                    }
                }
            }
        }

        return dist;

    }




    public int prim(I rs){ 
        Vertex<I,T> r = vertexCollection.get(rs);
        for(Map.Entry<I,Vertex<I,T>> i : vertexCollection.entrySet()){
            i.getValue().setDistance(Integer.MAX_VALUE);
            i.getValue().setColor(0);
            i.getValue().setoCol(0);
        }

        Comparator<Vertex<I,T>> comp = new Comparator<Vertex<I,T>>() {
            @Override
            public int compare(Vertex<I, T> o1, Vertex<I, T> o2) {
                return o1.getDistance()-o2.getDistance();
            }
        };

        ArrayList<Vertex<I,T>> q = new ArrayList<>();
        r.setDistance(0);
        r.setParent(null);
        for (Map.Entry<I,Vertex<I,T>> i : vertexCollection.entrySet()){
            q.add(i.getValue());
        }

        q.sort(comp);

        while(!q.isEmpty()){
            Vertex<I,T> u = q.remove(0);
            for(Map.Entry<I,Integer> i : adyacenseMatrix.get(u.getId()).entrySet()){
                if(i.getValue()>0){
                    if(searchVertex(i.getKey()).getColor()==0&&i.getValue()<searchVertex(i.getKey()).getDistance()){
                        searchVertex(i.getKey()).setDistance(i.getValue());
                        searchVertex(i.getKey()).setParent(u);
                    }
                }
            }
            u.setColor(2);
            q.sort(comp);
        }
        
        int weight = 0;

        for(Map.Entry<I,Vertex<I,T>> i : vertexCollection.entrySet()){
            if(i.getValue().getoCol()==0){
                Vertex<I,T> u = i.getValue();
                while(u.getParent()!=null){
                    if(u.getoCol()==0)weight+=u.getDistance();
                    u.setoCol(1);
                    u = u.getParent();
                }
            }
        }

        return weight;

    }





    public TreeSet<Edge<I,T>> Kruskal(){
        UnionFind<Vertex<I,T>> u = new UnionFind<>();
        for(Vertex<I,T> item : this){
            u.makeSet(item);
        }
        ArrayList<Edge<I,T>> edges = new ArrayList<>();
        for (Vertex<I,T> item : this){
              for (Map.Entry<I,Integer> i : adyacenseMatrix.get(item.getId()).entrySet()){
                if(i.getValue()>0) edges.add(new Edge<>(item, searchVertex(i.getKey()), i.getValue()));
            }
        }
        edges.sort(new Comparator<Edge<I,T>>() {
            @Override
            public int compare(Edge<I,T> o1, Edge<I,T> o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        TreeSet<Edge<I,T>> A = new TreeSet<>(new Comparator<Edge<I,T>>(){
            @Override
            public int compare(Edge<I,T> o1, Edge<I,T> o2) {
                return o1.equals(o2)?0:1;
            }
        });
        for(Edge<I,T> i : edges){
            if(u.find(i.getA())!=u.find(i.getB())){
                A.add(i);
                u.union(i.getA(), i.getB());
            }
        }

        return A;
    }

    public void clear(){
        vertexCollection.clear();
    }
    public static void main(String[] args) {
        
    }
    
}
class UnionFind<T>{
    private HashSet<T> allSet;
    private ArrayList<HashSet<T>> rootSet;

    public UnionFind(){
        allSet = new HashSet<>();
        rootSet = new ArrayList<>();
    }

    public void makeSet(T item){
        if(allSet.add(item)){
            HashSet<T> t = new HashSet<>();
            t.add(item);
            rootSet.add(t);
        }

    }
    public HashSet<T> find(T item){
        HashSet<T> toReturn = null;
        for (HashSet<T> i : rootSet){
            if(i.contains(item)){
                toReturn = i;
                break;
            }
        }
        return toReturn;
    }
    public void union(T a, T b){
        HashSet<T> unified = null;
        HashSet<T> toUnify = null;
        

        for(HashSet<T> item : rootSet){
            if(item.contains(a)){
                unified = item;
            }
            else if (item.contains(b)){
                toUnify = item;
            }
        }
        if(unified!=null&&toUnify!=null){
            rootSet.remove(toUnify);
            unified.addAll(toUnify);
        }
    }

}