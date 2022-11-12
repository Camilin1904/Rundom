package game.model;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class Controller {
    
    private Graph<String, Moveable> stage = new Graph<>();
    public static void main(String[] args) {
        Graph<String, Moveable> trial = new Graph<>();
        Enemy.getInstance().setMap(trial);
        trial.addVertex("1", Enemy.getInstance());
        Enemy.getInstance().setPosition(trial.searchVertex("1"));
        trial.addVertex("2", null);
        trial.addVertex("3", null);
        trial.addVertex("4", Player.getInstance());
        trial.addConnection("1", "4", "U", 9);
        trial.addConnection("2", "1", "L", 1);
        trial.addConnection("1", "2", "D", 3);
        trial.addConnection("2", "3", "R", 4);
        trial.addConnection("3", "4", "L", 1);

        Enemy.getInstance().updatePath();

        Controller c = new Controller();
        c.createScenario(10, 1.6);
    }

    public void createScenario(int size, double genConst){
        int[][] template = new int[size][size];
        for(int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                template[i][j] = (int)Math.round(Math.random()*genConst);
                stage.addVertex(i + "," + j, null);
            }
            System.out.println(Arrays.toString(template[i]));
        }

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                Vertex<String, Moveable> m = stage.searchVertex(i + "," + j);
                m.setUp(new Pair<Vertex<String, Moveable>, Integer>(stage.searchVertex((i-1) + "," + j), i>1? template[i-1][j] : 0));
                m.setDown(new Pair<Vertex<String, Moveable>, Integer>(stage.searchVertex((i+1) + "," + j), i<size? template[i+1][j] : 0));
                m.setRight(new Pair<Vertex<String, Moveable>, Integer>(stage.searchVertex(i + "," + j), j>1?template[i][j-1] : 0));
                m.setLeft(new Pair<Vertex<String, Moveable>, Integer>(stage.searchVertex(i + "," + j), j<size?template[i][j+1] : 0));
            }
        }



    }

}
