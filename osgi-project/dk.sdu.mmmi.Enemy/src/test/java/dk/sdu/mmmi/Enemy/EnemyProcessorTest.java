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
import dk.sdu.mmmi.commonEnemy.Enemy;
import dk.sdu.mmmi.player.Player;
import org.junit.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 *
 * @author Shark
 */
public class EnemyProcessorTest {
    
    public EnemyProcessorTest() {
    }
    
    private Entity player;
    private Entity enemy;
    private World world;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    Position enemyPosition;
    Position targetPosition;
    private GameData gameData;
    private float initialX = 0.0f;
    private float initialY = 0.0f;
    
    @Before
    public void setUp(){
        player = Mockito.mock(Player.class);
        world = Mockito.mock(World.class);
        enemy = Mockito.mock(Enemy.class);
        
        
        
        
        player.add(new Position(200,200, 3.1415f / 2));
        enemy.add(new Position(initialX,initialY,3.1415f / 2));
        enemy.add(new Movement(1000, 170, 250));
        
        world.addEntity(player);
        world.addEntity(enemy);
        
    }
    /**
     * Test of process method, of class EnemyProcessor.
     */
    @Test
    public void testProcess() {
        setUp();
        enemyPosition = enemy.getPart(Position.class);
        Movement enemyMovement = enemy.getPart(Movement.class);
        if (!world.getEntities(Player.class).isEmpty()) {
            player = (Player) world.getEntities(Player.class).get(0);
            targetPosition = player.getPart(Position.class);
        }

        float x = enemyPosition.getX();
        float y = enemyPosition.getY();

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

        assertNotEquals(initialX, enemyPosition.getX());
        assertNotEquals(initialX, enemyPosition.getY());
    }
    
    private float distanceCalculator(float currentX, float currentY, float targetX, float targetY){
        float distance = (float)Math.sqrt((targetX-currentX)*(targetX-currentX)+(targetY-currentY)*(targetY-currentY));
        return distance;
    }
    
}
