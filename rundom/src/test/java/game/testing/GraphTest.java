package game.testing;

import java.util.HashMap;

import game.model.*;
import junit.framework.TestCase;

public class GraphTest extends TestCase {
    
    private ListGraph<String, Integer> test;
    private MatrixGraph<String, Integer> test2;

    private void setUpScenario1(){
        test = new ListGraph<>();
        test.addVertex("a", 1);
        test.addVertex("b", 2);
        test.addVertex("c", 4);
        test.addVertex("d", 9);
        test.addVertex("e", 0);
        test.addConnection("a", "b", 1000);
        test.addConnection("b", "a", 1000);
        test.addConnection("a", "d", 400);
        test.addConnection("d", "a", 400);
        test.addConnection("d", "b", 500);
        test.addConnection("b", "d", 500);
        test.addConnection("c", "b", 350);
        test.addConnection("b", "c", 350);
        test.addConnection("e", "b", 100);
        test.addConnection("b", "e", 100);
        test.addConnection("c", "e", 200);
        test.addConnection("e", "c", 200);
    }
    private void setUpScenario2(){
        test = new ListGraph<>();
        test.addVertex("a", 1);
        test.addVertex("b", 2);
        test.addVertex("c", 4);
        test.addVertex("d", 9);
        test.addVertex("e", 0);
        test.addConnection("a", "b", 1000);
        test.addConnection("a", "d", 400);
        test.addConnection("b", "d", 500);
        test.addConnection("b", "c", 350);
        test.addConnection("e", "b", 100);
        test.addConnection("c", "e", 200);
    }

    private void setUpScenario3(){
        test2 = new MatrixGraph<>();
        test2.addVertex("a", 1);
        test2.addVertex("b", 2);
        test2.addVertex("c", 4);
        test2.addVertex("d", 9);
        test2.addVertex("e", 0);
        test2.addConnection("a", "b", 1000);
        test2.addConnection("b", "a", 1000);
        test2.addConnection("a", "d", 400);
        test2.addConnection("d", "a", 400);
        test2.addConnection("d", "b", 500);
        test2.addConnection("b", "d", 500);
        test2.addConnection("c", "b", 350);
        test2.addConnection("b", "c", 350);
        test2.addConnection("e", "b", 100);
        test2.addConnection("b", "e", 100);
        test2.addConnection("c", "e", 200);
        test2.addConnection("e", "c", 200);
    }
    private void setUpScenario4(){
        test2 = new MatrixGraph<>();
        test2.addVertex("a", 1);
        test2.addVertex("b", 2);
        test2.addVertex("c", 4);
        test2.addVertex("d", 9);
        test2.addVertex("e", 0);
        test2.addConnection("a", "b", 1000);
        test2.addConnection("a", "d", 400);
        test2.addConnection("b", "d", 500);
        test2.addConnection("b", "c", 350);
        test2.addConnection("e", "b", 100);
        test2.addConnection("c", "e", 200);
    }

    public void testDijkstra(){
        setUpScenario1();
        assertEquals(test.dijktraPath("a", "c").toString(), "[c, e, b, d]");
        assertEquals(test.dijktraPath("a", "e").toString(), "[e, b, d]");
        assertEquals(test.dijktraPath("a", "b").toString(), "[b, d]");
        assertEquals(test.dijktraPath("a", "d").toString(), "[d]");
    }

    public void testBFS(){
        setUpScenario2();
        assertTrue(test.checkConexivity("a"));
        assertFalse(test.checkConexivity("b"));
        setUpScenario1();
        assertTrue(test.checkConexivity("a"));
        assertTrue(test.checkConexivity("b"));
    }

    public void testFloydWarshall(){
        setUpScenario1();
        HashMap<String, HashMap<String,Integer>> h  = new HashMap<>();
        for(int i = 97; i<102; i++){
            char c = (char) i;
            h.put(c+"", new HashMap<>());
        }
        h.get("a").put("a", 0);
        h.get("a").put("b", 900);
        h.get("a").put("c", 1200);
        h.get("a").put("d", 400);
        h.get("a").put("e", 1000);
        h.get("b").put("a", 900);
        h.get("b").put("b", 0);
        h.get("b").put("c", 300);
        h.get("b").put("d", 500);
        h.get("b").put("e", 100);
        h.get("c").put("a", 1200);
        h.get("c").put("b", 300);
        h.get("c").put("c", 0);
        h.get("c").put("d", 800);
        h.get("c").put("e", 200);
        h.get("d").put("a", 400);
        h.get("d").put("b", 500);
        h.get("d").put("c", 800);
        h.get("d").put("d", 0);
        h.get("d").put("e", 600);
        h.get("e").put("a", 1000);
        h.get("e").put("b", 100);
        h.get("e").put("c", 200);
        h.get("e").put("d", 600);
        h.get("e").put("e", 0);

        assertEquals(test.floydWarshall(), h);
    }

    public void testPrim(){
        setUpScenario1();
        assertEquals(test.prim("a"), 1200);
        setUpScenario2();
        assertEquals(test.prim("a"), 1950);
        assertEquals(test.prim("b"), 1050);

    }

    public void testKruskal(){
        setUpScenario1();
        assertEquals(test.Kruskal().toString(), "[{{b,e},100}, {{c,e},200}, {{a,d},400}, {{b,d},500}]");

    } 


    public void testDijkstraMatrix(){
        setUpScenario3();
        assertEquals(test2.dijktraPath("a", "c").toString(), "[c, e, b, d]");
        assertEquals(test2.dijktraPath("a", "e").toString(), "[e, b, d]");
        assertEquals(test2.dijktraPath("a", "b").toString(), "[b, d]");
        assertEquals(test2.dijktraPath("a", "d").toString(), "[d]");
    }

    public void testBFSMatrix(){
        setUpScenario4();
        assertTrue(test2.checkConexivity("a"));
        assertFalse(test2.checkConexivity("b"));
        setUpScenario3();
        assertTrue(test2.checkConexivity("a"));
        assertTrue(test2.checkConexivity("b"));
    }

    public void testFloydWarshallMatrix(){
        setUpScenario3();
        HashMap<String, HashMap<String,Integer>> h  = new HashMap<>();
        for(int i = 97; i<102; i++){
            char c = (char) i;
            h.put(c+"", new HashMap<>());
        }
        h.get("a").put("a", 0);
        h.get("a").put("b", 900);
        h.get("a").put("c", 1200);
        h.get("a").put("d", 400);
        h.get("a").put("e", 1000);
        h.get("b").put("a", 900);
        h.get("b").put("b", 0);
        h.get("b").put("c", 300);
        h.get("b").put("d", 500);
        h.get("b").put("e", 100);
        h.get("c").put("a", 1200);
        h.get("c").put("b", 300);
        h.get("c").put("c", 0);
        h.get("c").put("d", 800);
        h.get("c").put("e", 200);
        h.get("d").put("a", 400);
        h.get("d").put("b", 500);
        h.get("d").put("c", 800);
        h.get("d").put("d", 0);
        h.get("d").put("e", 600);
        h.get("e").put("a", 1000);
        h.get("e").put("b", 100);
        h.get("e").put("c", 200);
        h.get("e").put("d", 600);
        h.get("e").put("e", 0);

        assertEquals(test2.floydWarshall(), h);
    }

    public void testPrimMatrix(){
        setUpScenario3();
        assertEquals(test2.prim("a"), 1200);
        setUpScenario4();
        assertEquals(test2.prim("a"), 1950);
        assertEquals(test2.prim("b"), 1050);

    }

    public void testKruskalMatrix(){
        setUpScenario3();
        assertEquals(test2.Kruskal().toString(), "[{{b,e},100}, {{c,e},200}, {{a,d},400}, {{b,d},500}]");

    } 
}
