package game.model;

public class Edge<I extends Comparable<I>,T> {

    Vertex<I,T> a;
    Vertex<I,T> b;
    Integer weight;

    public Edge(Vertex<I,T> a, Vertex<I,T> b, int weight){
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    public Vertex<I, T> getA() {
        return a;
    }

    public Vertex<I, T> getB() {
        return b;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        
        return "{{" + a + "," + b + "}" + "," + weight + "}";
    }
}
