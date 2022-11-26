package game.model;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Controller {
    private Player player;

    private Player actual;

    private Scoreboard sb = new Scoreboard();
    
    private VertexGraph<String, Moveable> stage;

    private ArrayList<String> orderedList;

    private Random rand = new Random(System.currentTimeMillis());

    private int[][] temp;

    private ArrayList<Enemy> extraEnemies;

    public Controller(boolean type){
        if(!type){
            stage = new ListGraph<>();
        }
        else stage = new MatrixGraph<>();
    }

    /*/**
     * Creates the graph with the starting enemy and player position, it is generated randomly 
     * with some tiles disconected and some with a higher weight, this will affect how the enemy
     * tracks the player. It returns the template used to generate the graphs for it to be used
     * to draw the visual representation of the stage.
     * @param size it will always be square shaped, therefore this is both its withs and lenght
     * 
     * @param genConst a number that will determine how common are blank spaces and how common are obstacles
     *   
     * @return the template generated to help the creation of the graph
     */
    public int[][] createScenario(int size, double genConst){
        stage.clear();
        Enemy.getInstance().setTarget(actual);
        boolean check = false;
        String k = "";
        int[][] template = new int[size][size];
        while(!check){
            Enemy.getInstance().setMap(stage);

            for(int i=0; i<size; i++){
                for (int j=0; j<size; j++){
                    System.out.println("o");
                    template[i][j] = (int)Math.round(rand.nextDouble()*genConst);
                    stage.addVertex(i + "," + j, null);
                }
                System.out.println(Arrays.toString(template[i]));
                k += Arrays.toString(template[i]) + "\n ";
            }
    
            //JOptionPane.showMessageDialog(null, k);
    
            for(int i=0; i<size; i++){
                for(int j=0; j<size; j++){
                    Vertex<String, Moveable> m = stage.searchVertex(i + "," + j);
                    if(template[i][j]!=0){
                        if(i>0&&template[i-1][j]!=0) stage.addConnection(i + "," + j, (i-1) + "," + j, "U", template[i-1][j]);
                        if(i<size-1&&template[i+1][j]!=0) stage.addConnection(i + "," + j, (i+1) + "," + j, "D", template[i+1][j]);
                        if(j>0&&template[i][j-1]!=0) stage.addConnection(i + "," + j, i + "," + (j-1), "L", template[i][j-1]);
                        if(j<size-1&&template[i][j+1]!=0) stage.addConnection(i + "," + j, i + "," + (j+1), "R", template[i][j+1]);
                    }
                    m.setType(template[i][j]);
                }
            }
            int i = (int)(rand.nextDouble()*size),j = (int)(rand.nextDouble()*size);
            boolean proceed = false;
            Vertex<String, Moveable> u = null;
            while (!proceed){
                if(template[i][j]!=0){
                    u = stage.searchVertex(i + "," + j);
                    stage.addValue(i + "," + j,Enemy.getInstance());
                    Enemy.getInstance().setPosition(u);
                    proceed = true;
                }
                if(!proceed){
                    double l = 0;
                    l = rand.nextDouble();
                    l = l*size-0.01;
                    i = ((int)l);
                    l = rand.nextDouble();
                    l = l*size;
                    j = (int)l;
                    if(i==0&&j==0)rand = new Random(System.currentTimeMillis());
                }
            }

            check = stage.checkConexivity(u.getId());
        }
        int i = (int)(rand.nextDouble()*size),j = (int)(rand.nextDouble()*size);
        boolean proceed = false;
        int dist = 0;
        while(dist<3){
            stage.addValue(i + "," + j, null);
            i = (int)(rand.nextDouble()*size);
            j = (int)(rand.nextDouble()*size);
            while (!proceed){
                System.out.println("i");
                if(template[i][j]!=0&&(stage.searchVertex(i + "," + j)).getValue()!=Enemy.getInstance()) {
                    stage.addValue(i + "," + j, actual);
                    actual.setPosition(stage.searchVertex(i + "," + j));
                    System.out.println(i + "," + j);
                    proceed = true;
                }
                if(!proceed){
                    double l = 0;
                    l = rand.nextDouble();
                    l = l*size-0.01;
                    i = ((int)l);
                    l = rand.nextDouble();
                    l = l*size;
                    j = (int)l;
                    rand = new Random(System.currentTimeMillis());
                }
            }
            
            dist = Enemy.getInstance().updatePath();
            proceed = false;
        }
        
        temp = template;
        return template;

    }




    public String[] generateKeyPositions(int numKeys){
        int keyNum = 0;
        String[] keys = new String[numKeys];
        while(keyNum<numKeys){
            for(Vertex<String,Moveable> item : stage){
                if(temp[Integer.parseInt(item.getId().charAt(0) + "")][Integer.parseInt(item.getId().charAt(2) + "")]!=0&&Math.random()>0.95&&keyNum<numKeys){
                    item.setHasKey(true);
                    keys[keyNum] = item.getId();
                    keyNum++;
                }
            }
        }
        return keys;
    }




    public int[][] createFinalScenario(int size, double genConst){
        extraEnemies = new ArrayList<>();
        extraEnemies.add(new Enemy());
        extraEnemies.add(new Enemy());
        stage.clear();
        Enemy.getInstance().setTarget(actual);
        extraEnemies.get(0).setTarget(actual);
        extraEnemies.get(1).setTarget(actual);
        boolean check = false;
        String k = "";
        int[][] template = new int[size][size];
        while(!check){
            Enemy.getInstance().setMap(stage);
            extraEnemies.get(0).setMap(stage);
            extraEnemies.get(1).setMap(stage);
            for(int i=0; i<size; i++){
                for (int j=0; j<size; j++){
                    int help = 0;
                    template[i][j] = (help = (int)Math.round(rand.nextDouble()*genConst))>=2?1:help;
                    stage.addVertex(i + "," + j, null);
                }
                System.out.println(Arrays.toString(template[i]));
                k += Arrays.toString(template[i]) + "\n ";
            }
    
            //JOptionPane.showMessageDialog(null, k);
    
            for(int i=0; i<size; i++){
                for(int j=0; j<size; j++){
                    Vertex<String, Moveable> m = stage.searchVertex(i + "," + j);
                    if(template[i][j]!=0){
                        if(i>0&&template[i-1][j]!=0) stage.addConnection(i + "," + j, (i-1) + "," + j, "U", template[i-1][j]);
                        if(i<size-1&&template[i+1][j]!=0) stage.addConnection(i + "," + j, (i+1) + "," + j, "D", template[i+1][j]);
                        if(j>0&&template[i][j-1]!=0) stage.addConnection(i + "," + j, i + "," + (j-1), "L", template[i][j-1]);
                        if(j<size-1&&template[i][j+1]!=0) stage.addConnection(i + "," + j, i + "," + (j+1), "R", template[i][j+1]);
                    }
                    m.setType(template[i][j]);
                }
            }
            int i = (int)(rand.nextDouble()*size),j = (int)(rand.nextDouble()*size);
            boolean proceed = false;
            Vertex<String, Moveable> u = null;
            while (!proceed){
                if(template[i][j]!=0){
                    u = stage.searchVertex(i + "," + j);
                    stage.addValue(i + "," + j,Enemy.getInstance());
                    Enemy.getInstance().setPosition(u);
                    proceed = true;
                }
                if(!proceed){
                    double l = 0;
                    l = rand.nextDouble();
                    l = l*size-0.01;
                    i = ((int)l);
                    l = rand.nextDouble();
                    l = l*size;
                    j = (int)l;
                    if(i==0&&j==0)rand = new Random(System.currentTimeMillis());
                }
            }

            for (Enemy e : extraEnemies){
                i = (int)(rand.nextDouble()*size);
                j = (int)(rand.nextDouble()*size);
                proceed = false;
                while (!proceed){
                    if(template[i][j]!=0&& (u =stage.searchVertex(i + "," + j)).getValue()==null){
                        stage.addValue(i + "," + j,e);
                        e.setPosition(u);
                        proceed = true;
                    }
                    if(!proceed){
                        double l = 0;
                        l = rand.nextDouble();
                        l = l*size-0.01;
                        i = ((int)l);
                        l = rand.nextDouble();
                        l = l*size;
                        j = (int)l;
                        if(i==0&&j==0)rand = new Random(System.currentTimeMillis());
                    }
                }
            }

            check = stage.checkConexivity(u.getId());
        }
        int i = (int)(rand.nextDouble()*size),j = (int)(rand.nextDouble()*size);
        boolean proceed = false;
        int dist = 0;
        while(dist<3){
            stage.addValue(i + "," + j, null);
            i = (int)(rand.nextDouble()*size);
            j = (int)(rand.nextDouble()*size);
            while (!proceed){
                System.out.println("i");
                if(template[i][j]!=0&&(stage.searchVertex(i + "," + j)).getValue()!=Enemy.getInstance()) {
                    stage.addValue(i + "," + j, actual);
                    actual.setPosition(stage.searchVertex(i + "," + j));
                    System.out.println(i + "," + j);
                    proceed = true;
                }
                if(!proceed){
                    double l = 0;
                    l = rand.nextDouble();
                    l = l*size-0.01;
                    i = ((int)l);
                    l = rand.nextDouble();
                    l = l*size;
                    j = (int)l;
                    rand = new Random(System.currentTimeMillis());
                }
            }
            PriorityQueue<Integer> distList = new PriorityQueue<>();
            distList.add(Enemy.getInstance().updatePath());
            distList.add(extraEnemies.get(0).updatePath());
            distList.add(extraEnemies.get(1).updatePath());
            System.out.println(Enemy.getInstance().getPath());
            System.out.println(extraEnemies.get(0).getPath());
            System.out.println(extraEnemies.get(1).getPath());
            System.out.println(Enemy.getInstance().getPath().lastElement().equals(extraEnemies.get(0).getPath().lastElement())&&Enemy.getInstance().getPath().lastElement().equals(extraEnemies.get(1).getPath().lastElement())&&extraEnemies.get(1).getPath().lastElement().equals(extraEnemies.get(0).getPath().lastElement()));
            dist = distList.poll();
        }
        temp = template;
        return template;

    }
    //DATA
    /*public void loadData(){
        try {
            File file=new File("Countries.json");
            FileInputStream fis=new FileInputStream(file);
            BufferedReader reader= new BufferedReader(new InputStreamReader(fis));
            String json="";
            String line;
            while ((line=reader.readLine())!=null){
                json+=line;
            }
            fis.close();
            Gson gson=new Gson();
            Country[] country=gson.fromJson(json, Country[].class);
            arrCountry.addAll(Arrays.asList(country));

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            File file=new File("Cities.json");
            FileInputStream fis=new FileInputStream(file);
            BufferedReader reader= new BufferedReader(new InputStreamReader(fis));
            String json="";
            String line;
            while ((line=reader.readLine())!=null){
                json+=line;
            }
            fis.close();
            Gson gson=new Gson();
            City[] cities=gson.fromJson(json,City[].class);
            for(int i=0;i<arrCountry.size();i++){
                for (int j=0;j<cities.length;j++){
                    if(arrCountry.get(i).getId().equals(cities[j].getCountryID())){
                        arrCountry.get(i).addCity(cities[i].getId(),cities[i].getName(),cities[i].getCountryID(),cities[i].getPopulation());
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }*/
    public ArrayList<String> inorder(){
        orderedList = new ArrayList<>();
        inorder(player);

        return orderedList;

    }
    private void inorder(Player current){
        if(current==null){
            return;
        }
        inorder(current.getLeft());
        orderedList.add(current.getName()+": "+current.getScore());
        inorder(current.getRight());
    }

    public Player getActual() {
        return actual;
    }

    public void setActual(Player actual) {
        this.actual = actual;
    }
    public VertexGraph<String, Moveable> getStage() {
        return stage;
    }

    public ArrayList<Pair<String, Double>> updateLeaderboard(){
        sb.clear();
        return sb.printScore(sb.getRoot(), 1);
    }

    public void insert(Player current) {
        if (sb.getRoot() == null) {
            sb.setRoot(current);
        } else {
            sb.insert(current, sb.getRoot());
        }
    }

    public ArrayList<Enemy> getExtraEnemies() {
        return extraEnemies;
    }
}
