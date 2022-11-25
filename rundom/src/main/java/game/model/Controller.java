package game.model;

import java.io.*;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class Controller {
    private Player player;
    
    private MatrixGraph<String, Moveable> stage = new MatrixGraph<>();
    public static void main(String[] args) {
        /*MatrixGraph<String, Moveable> trial = new MatrixGraph<>();
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

        Enemy.getInstance().updatePath();*/

        Controller c = new Controller();
        c.createScenario(3, 1.6);

        Enemy.getInstance().updatePath();


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
        Enemy.getInstance().setTarget(player);
        boolean check = true;
        String k = "";
        int[][] template = new int[size][size];
        while(check){
            Enemy.getInstance().setMap(stage);

            for(int i=0; i<size; i++){
                for (int j=0; j<size; j++){
                    template[i][j] = (int)Math.round(Math.random()*genConst);
                    stage.addVertex(i + "," + j, null);
                }
                //System.out.println(Arrays.toString(template[i]));
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
            int i = (int)(Math.random()*size),j = (int)(Math.random()*size);
            boolean proceed = false;
            Vertex<String, Moveable> u = null;
            while (!proceed){
                System.out.println("o:" + i + "," + j);
                if(template[i][j]!=0){
                    u = stage.searchVertex(i + "," + j);
                    stage.addValue(i + "," + j,Enemy.getInstance());
                    Enemy.getInstance().setPosition(u);
                    proceed = true;
                }
                if(!proceed){
                    i = (int)Math.random()*size;
                    j = (int)Math.random()*size;
                }
            }

            check = stage.checkConexivity(u.getId());
        }
        int i = (int)(Math.random()*size),j = (int)(Math.random()*size);
        boolean proceed = false;
        while (!proceed){
            System.out.println("i");
            if(template[i][j]!=0&&stage.searchVertex(i + "," + j).getValue()!=Enemy.getInstance()) {
                stage.addValue(i + "," + j, player);
                proceed = true;
            }
            if(!proceed){
                i = (int)(Math.random()*size);
                j = (int)(Math.random()*size);
            }
        }
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
    //ABB
    public void insert(String name,double points){
        Player n=new Player(name,points);
        if(player==null){
            player=n;
        }else{
            insert(n,player);
        }
    }
    private void insert(Player input,Player current){
        if(input.getScore()< current.getScore()){
            //Left
            if(current.getLeft()!=null){
                insert(input,current.getLeft());
            }else{
                current.setLeft(input);
            }
        }
        else if(input.getScore()>current.getScore()){
            //Right
            if(current.getRight()!=null){
                insert(input,current.getRight());
            }else{
                current.setRight(input);
            }
        }else{
            //Equal
            Player aux = current.getLeft();
            current.setLeft(input);
            input.setLeft(aux);
            return;
        }
    }
    public void inorder(){

        inorder(player);

    }
    private void inorder(Player current){
        if(current==null){
            return;
        }
        inorder(current.getLeft());
        System.out.println(current.getName()+": "+current.getScore());
        inorder(current.getRight());
    }

}
