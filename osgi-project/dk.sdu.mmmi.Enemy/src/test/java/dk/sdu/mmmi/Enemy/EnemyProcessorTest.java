/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.Enemy;

import com.badlogic.gdx.Game;
import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.entitypart.Movement;
import dk.sdu.mmmi.common.data.entitypart.Position;
import dk.sdu.mmmi.common.services.IEntityProcessingService;
import dk.sdu.mmmi.commonEnemy.Enemy;
import dk.sdu.mmmi.player.Player;
import org.junit.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 *
 * @author Shark
 */
public class EnemyProcessorTest {
    
//    Entity enemy;
//    Entity player;
    Entity player = new Player();
    Entity enemy = new Enemy();
    Position targetPosition;
    float initialX = 0;
    float initialY = 0;
    boolean left;
    boolean right;
    boolean up;
    boolean down;
    
    float x;
    float y;
    
    World world = new World();
    GameData gameData = new GameData();
    
    EnemyProcessor ep = new EnemyProcessor();
    
    public EnemyProcessorTest() {
    }
    
    
//    @Rule public MockitoRule rule = MockitoJUnit.rule();
    
    @Before
    public void setUp(){
        enemy.add(new Movement(1000, 170, 250));
        enemy.add(new Position(0, 0, 3.1415f / 2));
        enemy.setRadius(8);
        
        player.add(new Position(200,200,3.1415f / 2));
        
        world.addEntity(player);
        world.addEntity(enemy);
    }
    /**
     * Test of process method, of class EnemyProcessor.
     */
    @Test
    public void testProcess() {
        setUp();
        Position enemyInitialPosition = enemy.getPart(Position.class);
        assertEquals(initialX, enemyInitialPosition.getX());
        assertEquals(initialY, enemyInitialPosition.getY());
        
//        for (Entity i : world.getEntities(Enemy.class)) {
//            enemy = i;
                
            Position enemyPosition = enemy.getPart(Position.class);
            Movement enemyMovement = enemy.getPart(Movement.class);
            if (!world.getEntities(Player.class).isEmpty()) {
                player = (Player) world.getEntities(Player.class).get(0);
                targetPosition = player.getPart(Position.class);
            }
         
            x = enemyPosition.getX();
            y = enemyPosition.getY();
            
            float playerX = targetPosition.getX();
            float playerY = targetPosition.getY();
            
  
            float dist = distanceCalculator(x, y, playerX, playerY);
            if (dist > 40) {
                if (x > playerX) {
                    left = true;
                    right = false;
                }
                if (x < playerX) {
                    right = true;
                    left = false;
                }
                if (y > playerY) {
                    down = true;
                    up = false;
                }
                if (y < playerY) {
                    up = true;
                    down = false;
                }
            }
            
            enemyMovement.setLeft(left);
            enemyMovement.setRight(right);
            enemyMovement.setDown(down);
            enemyMovement.setUp(up);

            enemyMovement.process(gameData, enemy);
            enemyPosition.process(gameData, enemy);
            
            Position newPos = enemy.getPart(Position.class);
            float newX = newPos.getX();
//        }
        
        Position enemyNewPosition = enemy.getPart(Position.class);
        
        assertNotEquals(initialX, newX);
        assertNotEquals(initialY, enemyNewPosition.getY());
    }
    
    private float distanceCalculator(float currentX, float currentY, float targetX, float targetY){
        float distance = (float)Math.sqrt((targetX-currentX)*(targetX-currentX)+(targetY-currentY)*(targetY-currentY));
        return distance;
    }
}
