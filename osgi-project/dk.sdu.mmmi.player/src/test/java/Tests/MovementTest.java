/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.GameKeys;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.entitypart.Movement;
import dk.sdu.mmmi.common.data.entitypart.Position;
import dk.sdu.mmmi.player.Player;
import dk.sdu.mmmi.player.PlayerPlugin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Daniel Tran
 */
public class MovementTest {
    World world = null;
    GameData gameData = null;
    Entity player = null;
    Movement movement = null;
    Position position = null;
    
    
    public MovementTest() {
    }
    
    @BeforeEach
    public void setUp() {
        this.world = new World();
        this.gameData = new GameData();
        this.gameData.setDelta(1f);

        PlayerPlugin plugin = new PlayerPlugin();
        plugin.start(gameData, world);

        for (Entity player : world.getEntities(Player.class)) {
            this.player = player;
            this.movement = player.getPart(Movement.class);
            this.position = player.getPart(Position.class);
        }  
    }

    @Test
    public void playerMovementTest() throws InterruptedException {
        float startingXpos = 0;
        float startingYpos = 0;

        //Gets the starting position of player before movement
        startingXpos = position.getX();
        startingYpos = position.getY();

        //True inside of method represents: left key pressed down = true
        movement.setLeft(true);
        movement.process(gameData, player);

        //Get new postion of player after movement
        float newXPos = position.getX();
        
        //NOT EQUALTS to confirm that the player has moved and is NOT in starting position
        assertNotEquals(startingXpos, newXPos);

        //Y coordinate movement this time
        movement.setUp(true);
        movement.process(gameData, player);
        
        float newYPos = position.getY();
        assertNotEquals(startingYpos, newYPos);
        
    }
}
