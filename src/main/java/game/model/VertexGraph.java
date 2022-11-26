package game.model;

public abstract class VertexGraph<I extends Comparable<I>,T> implements Graph<I,T>, Iterable<Vertex<I,T>>{
    public Vertex<I,T> containerOf(T value){
        return null;
    }

    public Vertex<I,T> searchVertex(I id){
        return null;
    }
}
