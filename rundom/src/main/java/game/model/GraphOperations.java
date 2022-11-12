package game.model;

import java.util.*;

public class GraphOperations {

    public static <I, T> void BFS(Vertex<I, T> s, Graph<I, T> graph){
        ArrayList<Vertex<I, T>> queue = new ArrayList<>();
        for(Vertex<I, T> n : graph){
            n.setColor(0);
            n.setDistance(Integer.MAX_VALUE);
            n.setParent(null);
        }
        s.setColor(1);
        s.setDistance(0);
        s.setParent(null);
        queue.add(s);
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

    public static <I, T> void DFS(Graph<I, T> graph){
        for(Vertex<I, T> n : graph){
            n.setColor(0);
            n.setParent(null);
        }
        int time = 0;
        for (Vertex<I, T> n : graph){
            if(n.getColor()==0) time = DFSVisit(n, time, graph);
        }
    }

    public static <I, T> int DFSVisit(Vertex<I, T> vertex, int time, Graph<I, T> graph){ 
        System.out.println(vertex.getValue().getClass());
        time++;
        vertex.setInitial(time);
        vertex.setColor(1);
        for (Pair<Vertex<I, T>,Integer> n : vertex.getAdyacentVertex()){
            if(n.getA().getColor()==0){
                n.getA().setParent(vertex);
                DFSVisit(n.getA(), time, graph);
            }
        }
        vertex.setColor(2);
        time++;
        vertex.setEnd(time);
        return time;
    }


    public static <I, T> Stack<Vertex<I, T>> dijktraPath(Graph<I, T> graph, Vertex<I, T> start, Vertex<I, T> end){
        PriorityQueue<Vertex<I, T>> vertexes = new PriorityQueue<>(new Comparator<Vertex<I, T>>() {
            @Override
            public int compare(Vertex<I, T> o1, Vertex<I, T> o2) {
                return o1.getDistance()-o2.getDistance();
            }
        });

        for(Vertex<I, T> item : graph){
            item.setDistance(Integer.MAX_VALUE);
            item.setParent(null);
            vertexes.add(item);
        }

        Stack<Vertex<I, T>> path = new Stack<>();

        start.setDistance(0);
        while(!vertexes.isEmpty()){
            Vertex<I, T> u = vertexes.poll();
            for (Pair<Vertex<I, T>,Integer> item : u.getAdyacentVertex()){
                int dist = item.getB() + u.getDistance();
                if(dist<item.getA().getDistance()){
                    item.getA().setDistance(dist);
                    item.getA().setParent(u);
                }
            }
        }

        Vertex<I, T> u = end;

        while(u!=start){
            path.push(u);
            u = u.getParent();
        }
        return path;
    }

    public static void main(String[] args) {
        Graph<Integer, Integer> g = new Graph<>();

        g.addVertex(2, 2);
        g.addVertex(4, 4);
        g.addVertex(7, 7);
        g.addConnection(2, 4, "R", 7);
        g.addConnection(2, 7, "L", 2);
        g.addConnection(7, 4, "D", 3);

        //DFS(g);

        Stack<Vertex<Integer, Integer>> v = dijktraPath(g, g.searchVertex(2), g.searchVertex(4));

        while (!v.isEmpty()) System.out.println(v.pop());
        /*for(Vertex<Integer> i : g){
            System.out.println(i.getValue());
        }*/
    }
    
}
