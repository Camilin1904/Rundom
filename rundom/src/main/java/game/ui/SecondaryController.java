package game.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

import game.model.Controller;
import game.model.Enemy;
import javax.swing.JOptionPane;

public class SecondaryController implements Initializable {

    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;
    private boolean isRunning = true;
    private Image bg;
    private Image wall;
    private Image key;
    private Image slime;
    private Controller ctrl;

    private double speedStat = 1;

    private int waitTime = 1000;
    private long timer = 0;
    private long timerPlayer = 0;
    private int timeoutPlayer = 0;

    private int[][] template;

    private final double[] constants = {1.6, 1.3, 1.7, 2};


    //Elementos gráficos
    private Avatar avatar;
    private EnemyAvatar enemyAvatar;

    private boolean gameState = false;

    private int numKeys = 3;

    private int numFloors = 5;

    private ArrayList<EnemyAvatar> extraEnemies = null;
    private long[] timerExtra = {0,0};
    private int[] waitTimeExtra = {1000,1000};


    //Estados de las teclas
    boolean Wpressed = false;
    boolean Apressed = false;
    boolean Spressed = false;
    boolean Dpressed = false;

    Thread enemyAI = new Thread(new Runnable(){
        public void run() {
            Enemy.getInstance().move();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    });



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boolean ending = false;
        boolean check = true;
        ctrl = Rundom.ctrl;
        ctrl.getActual().getPosScore().setRoom(ctrl.getActual().getPosScore().getRoom()+1);
        if(ctrl.getActual().getPosScore().getRoom()>numFloors){
            ctrl.getActual().getPosScore().setRoom(1);
            ctrl.getActual().getPosScore().setFloor(ctrl.getActual().getPosScore().getFloor()+1);
        }
        template = ctrl.createScenario(10, constants[ctrl.getActual().getPosScore().getFloor()-1]);
        ctrl.generateKeyPositions(3);
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        avatar = new Avatar(canvas, "nessfuzzy.png");
        switch(ctrl.getActual().getPosScore().getFloor()){
            case(1):
                enemyAvatar = new EnemyAvatar(canvas, "starman.png");
                break;
            case(2):
                enemyAvatar = new EnemyAvatar(canvas, "leel.png");
                break;
            case(3):
                enemyAvatar = new EnemyAvatar(canvas, "ness.png");
                break;
            case(4):
                enemyAvatar = new EnemyAvatar(canvas, "iceSi.png");
                ending = ctrl.getActual().getPosScore().getRoom()==numFloors;
                break;
        }
        speedStat = 1-((ctrl.getActual().getPosScore().getRoom()*0.05)+ctrl.getActual().getPosScore().getFloor()*0.07);
        avatar.setCharacterInside(Rundom.ctrl.getActual());
        enemyAvatar.setCharacterInside(Enemy.getInstance());
        canvas.setOnKeyPressed(this::onKeyPressed);
        String uri = "file:"+ Rundom.class.getResource("wall.png").getPath();
        wall = new Image(uri);
        String uri2 = "file:"+ Rundom.class.getResource("suelo.png").getPath();
        bg = new Image(uri2);
        String uri3 = "file:"+ Rundom.class.getResource("key.png").getPath();
        key = new Image(uri3);
        String uri4 = "file:"+ Rundom.class.getResource("slime.png").getPath();
        slime = new Image(uri4);
        while(check){
            try{
                if(ending){
                    speedStat = 0.4;
                    template = ctrl.createFinalScenario(10,5);
                    ctrl.generateKeyPositions(9);
                    avatar.setCharacterInside(ctrl.getActual());
                    extraEnemies = new ArrayList<>();
                    enemyAvatar = new EnemyAvatar(canvas, "iceSi.png");
                    enemyAvatar.setCharacterInside(Enemy.getInstance());
                    extraEnemies.add(new EnemyAvatar(canvas, "evilstarman.png"));
                    extraEnemies.add(new EnemyAvatar(canvas, "evilfront.png"));
                    ArrayList<Enemy> exEn = ctrl.getExtraEnemies();
                    extraEnemies.get(0).setCharacterInside(exEn.get(0));
                    extraEnemies.get(1).setCharacterInside(exEn.get(1));
                }
                check = false;
            }
            catch(NullPointerException e){}
        }

        draw();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if(System.currentTimeMillis()-timerPlayer>timeoutPlayer){
            System.out.println(keyEvent.getCode());
            String stp = "";
            
            if(keyEvent.getCode() == KeyCode.W){
                if((stp = avatar.move("U"))!=null) avatar.moveUp();
            }
            if(keyEvent.getCode() == KeyCode.A){
                if((stp = avatar.move("L"))!=null) avatar.moveLeft();
            }
            if(keyEvent.getCode() == KeyCode.S){
                if((stp = avatar.move("D"))!=null) avatar.moveDown(); 
            }
            if(keyEvent.getCode() == KeyCode.D){
                if((stp = avatar.move("R"))!=null) avatar.moveRight();
            }
            gameState = stp.equals("n");
            if(stp.equals("2")) {
                timeoutPlayer = 1000;
                timerPlayer = System.currentTimeMillis();
            }
        }
        

    }

    public void draw(){
        
        new Thread(
                ()->{
                    while(isRunning){
                        Platform.runLater(()->{
                            if(numKeys>0){
                                if(gameState&&isRunning){
                                    isRunning = false;
                                    JOptionPane.showMessageDialog(null, "Game Over");
                                    Rundom.showWindow("primary.fxml");
                                    Stage current = (Stage) canvas.getScene().getWindow();
                                    current.hide();
                                }
                                else{
                                    String go = "";
                                    if(-timer+System.currentTimeMillis()>waitTime*speedStat){
                                        go = Enemy.getInstance().move();
                                        timer = System.currentTimeMillis();
                                        waitTime = 1000;
                                        if(go!=null&&go.length()>=2){
                                            waitTime = waitTime*2;
                                        }
                                    }
                                    else if(timer==0)timer = System.currentTimeMillis();
                                    if(extraEnemies!=null){
                                        for(int i=0; i<2; i++){
                                            if(-timerExtra[i]+System.currentTimeMillis()>waitTimeExtra[i]*speedStat){
                                                go = extraEnemies.get(i).getCharacterInside().move();
                                                timerExtra[i] = System.currentTimeMillis();
                                                waitTimeExtra[i] = 1000;
                                                if(go!=null&&go.length()>=2){
                                                    waitTimeExtra[i] = waitTimeExtra[i]*2;
                                                }
                                            }
                                            else if(timerExtra[i]==0)timerExtra[i] = System.currentTimeMillis();
                                        }
                                    }
                                    drawBackground();
                                    enemyAvatar.draw();
                                    if(extraEnemies!=null){
                                        extraEnemies.get(0).draw();
                                        extraEnemies.get(1).draw();
                                    }
                                    int newNumKeys = 0;
                                    for(int i = 0; i<10; i++){
                                        for(int e=0; e<10; e++){
                                            if(template[i][e]==0){
                                                gc.save();
                                                gc.drawImage(wall, e*70, i*70, 70, 70);
                                                gc.restore();
                                            }
                                            if(template[i][e]==2){
                                                gc.save();
                                                gc.drawImage(slime, e*70, i*70, 70, 70);
                                                gc.restore();
                                            }
                                            if(Rundom.ctrl.getStage().searchVertex(i +"," + e).getHasKey()){
                                                newNumKeys++;
                                                gc.save();
                                                gc.drawImage(key, e*70, i*70, 70, 70);
                                                gc.restore();
                                            }
                                            
                                        }
                                    }
                                    numKeys = newNumKeys;
                                    gameState = go!=null&&go.equals("y");
                                    avatar.move();
                                }    
                            }
                            else{
                                isRunning = false;
                                JOptionPane.showMessageDialog(null, "Stage Completed");
                                Rundom.showWindow("canvasView.fxml");
                                Stage current = (Stage) canvas.getScene().getWindow();
                                current.hide();
                            }
                        });
                        //Sleep
                        try {
                            //enemyAI.notify();
                            Thread.sleep(17);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();

    }

    public void drawBackground(){
        gc.save();
        gc.drawImage(bg, 0,0, 700,700);
        gc.restore();

    }
    
    public void drawWalls(){

    }
    public void drawWall(int e, int i){
        new Thread(()->{

        }).start();;
    }

}