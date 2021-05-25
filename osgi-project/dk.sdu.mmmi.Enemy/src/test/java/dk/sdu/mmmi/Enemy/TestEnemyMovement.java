/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.Enemy;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.entitypart.Movement;
import dk.sdu.mmmi.common.data.entitypart.Position;
import dk.sdu.mmmi.commonEnemy.Enemy;
import dk.sdu.mmmi.player.Player;
import dk.sdu.mmmi.player.PlayerPlugin;
import org.junit.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Shark
 */
public class TestEnemyMovement {
    World world = null;
    GameData gameData = null;
    Entity player = null;
    Entity enemy = null;
    Position enemyPosition = null;
    Position targetPosition = null;
    Movement enemyMovement = null;
    boolean left;
    boolean right;
    boolean up;
    boolean down;

    float x;
    float y;

    public TestEnemyMovement() {
    }

    @BeforeEach
    public void setUp() {
        this.world = new World();
        this.gameData = new GameData();
        this.gameData.setDelta(1f);

        PlayerPlugin playerPlugin = new PlayerPlugin();
        playerPlugin.start(gameData, world);
        for (Entity i : world.getEntities(Player.class)) {
            this.player = i;
            this.targetPosition = i.getPart(Position.class);
        }

        EnemyPlugin enemyPlugin = new EnemyPlugin();
        enemyPlugin.start(gameData, world);
        for (Entity j : world.getEntities(Enemy.class)) {
            this.enemy = j;
            this.enemyMovement = j.getPart(Movement.class);
            this.enemyPosition = j.getPart(Position.class);
        }
        
        world.addEntity(player);
        world.addEntity(enemy);
    }

    @Test
    public void testProcess() {
        float enemyX = 0;
        float enemyY = 0;
        float targetX = 0;
        float targetY = 0;

        // Gets the initial position of the enemy before movement
        enemyX = enemyPosition.getX();
        enemyY = enemyPosition.getY();
        
        // Gets the position of the player
        targetX = targetPosition.getX();
        targetY = targetPosition.getY();
        
        // Sets the initial distance between the enemy and the player
        float initialDistance = distanceCalculator(enemyX, enemyY, targetX, targetY);

        // Checks which direction the enemy should go by comparing its coordinates with the players coordinates
        if (enemyX > targetX) {
            left = true;
            right = false;
        }
        if (enemyX < targetX) {
            right = true;
            left = false;
        }
        if (enemyY > targetY) {
            down = true;
            up = false;
        }
        if (enemyY < targetY) {
            up = true;
            down = false;
        }
        enemyMovement.setLeft(left);
        enemyMovement.setRight(right);
        enemyMovement.setDown(down);
        enemyMovement.setUp(up);
        
        enemyMovement.process(gameData, enemy);
        enemyPosition.process(gameData, enemy);
        
        // Sets the new position of the enemy after movement
        float newX = enemyPosition.getX();
        float newY = enemyPosition.getY();
        
        // Sets the new distance between the enemy and the player after movement
        float newDistance = distanceCalculator(newX, newY, targetX, targetY);
        
        // Tests to see if the distance between enemy and player has changed, by asserting NOT equals as the distance should have changed
        assertNotEquals(initialDistance, newDistance);
        
        // Tests to see if the enemy x and y coordinates have changed, by asserting NOT equals as the coordinates should have changed
        assertNotEquals(enemyX, newX);
        assertNotEquals(newY, enemyY);
    }

    private float distanceCalculator(float currentX, float currentY, float targetX, float targetY) {
        float distance = (float) Math.sqrt((targetX - currentX) * (targetX - currentX) + (targetY - currentY) * (targetY - currentY));
        return distance;
    }
}
