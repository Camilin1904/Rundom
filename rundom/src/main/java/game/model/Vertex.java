package game.model;

public class Vertex<T> {
    private T value;
    private Vertex<T> up;
    private Vertex<T> right;
    private Vertex<T> down;
    private Vertex<T> left;
    private Vertex<T> parent;
    private int distance;
    
    public Vertex(T value){
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Vertex<T> getParent() {
        return parent;
    }
    
    public void setParent(Vertex<T> parent) {
        this.parent = parent;
    }
    public Vertex<T> getDown() {
        return down;
    }
    public void setDown(Vertex<T> down) {
        this.down = down;
    }
    public Vertex<T> getLeft() {
        return left;
    }
    public void setLeft(Vertex<T> left) {
        this.left = left;
    }
    public Vertex<T> getRight() {
        return right;
    }
    public void setRight(Vertex<T> right) {
        this.right = right;
    }
    public Vertex<T> getUp() {
        return up;
    }
    public void setUp(Vertex<T> up) {
        this.up = up;
    }

}
