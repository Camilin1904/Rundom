package game.model;

import java.util.*;

public class Vertex<I, T> {
    private I id;
    private T value;
    private Pair<Vertex<I, T>,Integer> up;
    private Pair<Vertex<I, T>,Integer> right;
    private Pair<Vertex<I, T>,Integer> down;
    private Pair<Vertex<I, T>,Integer> left;
    private Vertex<I, T> parent;
    private ArrayList<Pair<Vertex<I, T>,Integer>> adyacentVertex;
    private int distance;
    private int color;
    private int initial;
    private int end;
    
    public Vertex(T value){
        this.value = value;
        adyacentVertex = new ArrayList<>();
    }

    public Vertex(I id, T value){
        this.id = id;
        this.value = value;
        adyacentVertex = new ArrayList<>();
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

    public Vertex<I, T> getParent() {
        return parent;
    }
    
    public void setParent(Vertex<I, T> parent) {
        this.parent = parent;
    }
    public Pair<Vertex<I, T>,Integer> getDown() {
        return down;
    }
    public void setDown(Pair<Vertex<I, T>,Integer> down) {
        adyacentVertex.remove(this.down);
        this.down = down;
        adyacentVertex.add(down);
    }
    public Pair<Vertex<I, T>,Integer> getLeft() {
        return left;
    }
    public void setLeft(Pair<Vertex<I, T>,Integer> left) {
        adyacentVertex.remove(this.left);
        this.left = left;
        adyacentVertex.add(left);
    }
    public Pair<Vertex<I, T>,Integer> getRight() {
        return right;
    }
    public void setRight(Pair<Vertex<I, T>,Integer> right) {
        adyacentVertex.remove(this.right);
        this.right = right;
        adyacentVertex.add(right);
    }
    public Pair<Vertex<I, T>,Integer> getUp() {
        return up;
    }
    public void setUp(Pair<Vertex<I, T>,Integer> up) {
        adyacentVertex.remove(this.up);
        this.up = up;
        adyacentVertex.add(up);
    }
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public ArrayList<Pair<Vertex<I, T>,Integer>> getAdyacentVertex() {
        return adyacentVertex;
    }
    public void setAdyacentVertex(ArrayList<Pair<Vertex<I, T>,Integer>> adyacentVertex) {
        this.adyacentVertex = adyacentVertex;
    }
    public void addAdyacentVertex(Pair<Vertex<I, T>,Integer> vertex){
        adyacentVertex.add(vertex);
    }
    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }
    public int getInitial() {
        return initial;
    }
    public void setInitial(int initial) {
        this.initial = initial;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    public I getId() {
        return id;
    }
    public void setId(I id) {
        this.id = id;
    }

}
