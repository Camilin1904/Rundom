package game.model;

import java.util.*;

public class UnionFind<T> {
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
