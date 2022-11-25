package game.model;

import java.util.*;

public class ListGraph<I extends Comparable<I>, T> implements Graph<I, T>, Iterable<Vertex<I, T>>{

    private HashMap<I, Vertex<I, T>> vertexCollection;
    private HashMap<T, Vertex<I, T>> aux;

    public ListGraph(){
        vertexCollection = new HashMap<>();
        aux = new HashMap<>();
    }

    @Override
    public void addVertex(I id, T toAdd) {
        Vertex<I, T> t = new Vertex<I, T>(id, toAdd);
        vertexCollection.put(id, t);
        if(toAdd!=null) aux.put(toAdd, t);
    }

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
    public void addConnection(I pointer, I pointed, int weight) {
        
        Pair<Vertex<I, T>,Integer> p = new Pair<Vertex<I, T>,Integer>(vertexCollection.get(pointed), weight);
        vertexCollection.get(pointer).getAdyacentVertex().add(p);
        
    }

    public void addValue(I id, T toAdd){
        searchVertex(id).setValue(toAdd);
        aux.put(toAdd, searchVertex(id));
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

    public void clear(){
        vertexCollection.clear();
        aux.clear();
    }

    public void BFS(I s){
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
            for(Pair<Vertex<I, T>, Integer> n : u.getAdyacentVertex()){
                if(n.getA().getColor()==0){
                    n.getA().setColor(1);
                    n.getA().setDistance(u.getDistance()+1);
                    n.getA().setParent(u);
                    queue.add(n.getA());
                }
            }
            u.setColor(2);
        }
    }


    

    public void DFS(){
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
        for (Pair<Vertex<I, T>,Integer> n : vertex.getAdyacentVertex()){
            if(n.getA().getColor()==0){
                n.getA().setParent(vertex);
                DFSVisit(n.getA(), time);
            }
        }
        vertex.setColor(2);
        time++;
        vertex.setEnd(time);
        return time;
    }


    public Stack<Vertex<I, T>> dijktraPath(I startID, I endID){
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
            for (Pair<Vertex<I, T>,Integer> item : u.getAdyacentVertex()){
                int dist = item.getB() + u.getDistance();
                if(dist<item.getA().getDistance()){
                    item.getA().setDistance(dist);
                    item.getA().setParent(u);
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

    public boolean checkConexivity(I start){
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
            for (Pair<Vertex<I,T>,Integer> i : item.getAdyacentVertex()){
                dist.get(item.getId()).replace(i.getA().getId(), i.getB());
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
            for(Pair<Vertex<I,T>, Integer> i : u.getAdyacentVertex()){
                if(i.getA().getColor()==0&&i.getB()<i.getA().getDistance()){
                    i.getA().setDistance(i.getB());
                    i.getA().setParent(u);
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
              for (Pair<Vertex<I,T>,Integer> i : item.getAdyacentVertex()){
                edges.add(new Edge<>(item, i.getA(), i.getB()));
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

    public static void main(String[] args) {
        ListGraph<String, String> g = new ListGraph<>();
        /*g.addVertex("1", "a");
        g.addVertex("2", "b");
        g.addVertex("3", "c");
        g.addVertex("4", "d");
        g.addConnection("1", "3", "R", -2);
        g.addConnection("2", "1", "R", 4);
        g.addConnection("2", "3", "R", 3);
        g.addConnection("3", "4", "R", 2);
        g.addConnection("4", "2", "R", -1);
        /*g.addConnection("b", "d", "R", 3);
        g.addConnection("d", "b", "R", 3);
        System.out.println(g.Kruskal());
        System.out.println(g.floydWarshall());*/

        g.addVertex("SF", "SF");
        g.addVertex("CH", "CH");
        g.addVertex("DE", "DE");
        g.addVertex("NY", "NY");
        g.addVertex("AT", "AT");
        g.addConnection("SF", "NY", "R", 2000);
        g.addConnection("NY", "SF", "R", 2000);
        g.addConnection("SF", "AT", "R", 2200);
        g.addConnection("AT", "SF", "R", 2200);
        g.addConnection("NY", "AT", "R", 800);
        g.addConnection("AT", "NY", "R", 800);
        g.addConnection("NY", "CH", "R", 1000);
        g.addConnection("CH", "NY", "R", 1000);
        g.addConnection("NY", "DE", "R", 1600);
        g.addConnection("DE", "NY", "R", 1600);
        g.addConnection("DE", "SF", "R", 900);
        g.addConnection("SF", "DE", "R", 900);
        g.addConnection("SF", "CH", "R", 1200);
        g.addConnection("CH", "SF", "R", 1200);
        g.addConnection("DE", "CH", "R", 1300);
        g.addConnection("CH", "DE", "R", 1300);
        g.addConnection("AT", "CH", "R", 700);
        g.addConnection("CH", "AT", "R", 700);

        System.out.println(g.prim("AT"));
    }
}