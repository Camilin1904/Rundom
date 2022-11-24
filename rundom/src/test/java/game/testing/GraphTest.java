package game.testing;

import game.model.*;

import junit.framework.TestCase;

public class GraphTest extends TestCase{
    
    private Graph<String, Integer> test;

    private void setUpScenario1(){
        test = new Graph<>();
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
        test = new Graph<>();
        test.addVertex("a", 1);
        test.addVertex("b", 2);
        test.addVertex("c", 4);
        test.addVertex("d", 9);
        test.addVertex("e", 0);
        test.addConnection("a", "b", 1000);
        //test.addConnection("b", "a", 1000);
        test.addConnection("a", "d", 400);
        //test.addConnection("d", "a", 400);
        //test.addConnection("d", "b", 500);
        test.addConnection("b", "d", 500);
        //test.addConnection("c", "b", 350);
        test.addConnection("b", "c", 350);
        test.addConnection("e", "b", 100);
        //test.addConnection("b", "e", 100);
        test.addConnection("c", "e", 200);
        //test.addConnection("e", "c", 200);
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
        test.BFS("a");
        assertTrue(test.checkConexivity("a"));
        test.BFS("b");
        assertFalse(test.checkConexivity("b"));
    }
}
