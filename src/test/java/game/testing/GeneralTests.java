package game.testing;

import junit.framework.TestCase;
import game.model.*;
import java.util.*;

public class GeneralTests extends TestCase{
    
    Controller ctrl;

    public void setUpScenario(){
        ctrl = new Controller(false);
        ctrl.setActual(new Player("test", 0));
    }

    public void setUpScenarioMatrix(){
        ctrl = new Controller(false);
        ctrl.setActual(new Player("test", 0));
    }



    public void testCreateScenario(){
        setUpScenario();
        assertNotNull(ctrl.createScenario(5, 1.6));
        assertNotNull(Enemy.getInstance().updatePath());
        assertNotNull(ctrl.getActual().getPosition());
    }

    public void testCreateScenarioMatrix(){
        setUpScenarioMatrix();
        assertNotNull(ctrl.createScenario(5, 1.6));
        assertNotNull(Enemy.getInstance().updatePath());
        assertNotNull(ctrl.getActual().getPosition());
    }

    public void testMoveEnemy(){
        setUpScenario();
        String u = Enemy.getInstance().getPath().toString();

        Enemy.getInstance().move();

        String o = Enemy.getInstance().getPath().toString();
        assertTrue(u.length()>o.toString().length());
    }

    public void testMoveEnemyMatrix(){
        setUpScenarioMatrix();
        String u = Enemy.getInstance().getPath().toString();

        Enemy.getInstance().move();

        String o = Enemy.getInstance().getPath().toString();
        assertTrue(u.length()>o.toString().length());
    }

    public void testGenerateKeyPosition(){
        setUpScenarioMatrix();
        ctrl.createScenario(5, 1.6);
        String[] hp =ctrl.generateKeyPositions(7);
        boolean flag=false;
        if(hp!=null){
            flag=true;
        }
        assertTrue(flag);
    }

    public void testABBAddPlayer(){
        setUpScenarioMatrix();
        ctrl.createScenario(5, 1.6);
        ctrl.insert(new Player("test", 0));
        ctrl.insert(new Player("test2", 1));
        boolean flag=false;
        if(ctrl.inorder()!=null){
            flag=true;
        }
        assertTrue(flag);

    }

}
