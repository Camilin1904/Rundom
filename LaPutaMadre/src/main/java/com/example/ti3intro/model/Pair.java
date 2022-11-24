package com.example.ti3intro.model;

public class Pair <A,B> {

    private A a;
    private B b;
    
    public Pair(A a, B b){
        this.a = a;
        this.b = b;
    }
    public A getA() {
        return a;
    }
    public void setA(A a) {
        this.a = a;
    }
    public B getB() {
        return b;
    }
    public void setB(B b) {
        this.b = b;
    }
    @Override
    public String toString() {
        return "\\" + a.toString() + "," + b.toString() + "/";
    }
}
