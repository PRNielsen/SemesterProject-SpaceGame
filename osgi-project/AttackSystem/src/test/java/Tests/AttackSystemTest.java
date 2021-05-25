/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import dk.sdu.mmmi.attack.AttackSystem;
import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.entitypart.Combat;
import dk.sdu.mmmi.common.data.entitypart.Health;
import dk.sdu.mmmi.common.data.entitypart.Movement;
import dk.sdu.mmmi.common.data.entitypart.Position;
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
public class AttackSystemTest {

    World world = null;
    GameData gameData = null;
    Entity player, enemy1, enemy2 = null;
    Movement movement = null;
    Position position = null;

    public AttackSystemTest() {
    }

    @BeforeEach
    public void setUp() {
        this.world = new World();
        this.gameData = new GameData();

        float deacceleration = 1000;
        float acceleration = 300;
        float maxSpeed = 300;
        float radians = 3.1415f / 2;
        int atkDmg = 1;
        int range = 50;

        //Providing entity player with entityparts
        player = new Entity();
        player.setRadius(8);
        player.add(new Movement(deacceleration, acceleration, maxSpeed));
        player.add(new Position(400, 200, radians));
        player.add(new Health(1));
        player.add(new Combat(atkDmg, range));
        world.addEntity(player);

        //Providing entity enemy1 with entityparts
        enemy1 = new Entity();
        enemy1.setRadius(4);
        enemy1.add(new Movement(deacceleration, acceleration, maxSpeed));
        enemy1.add(new Position(400, 225, radians));
        enemy1.add(new Health(3));
        world.addEntity(enemy1);
        
        enemy2 = new Entity();
        enemy2.setRadius(4);
        enemy2.add(new Movement(deacceleration, acceleration, maxSpeed));
        enemy2.add(new Position(700, 700, radians));
        enemy2.add(new Health(3));
        world.addEntity(enemy2);

    }

    @Test
    public void performAttackTest() {
        Combat playerCombat = player.getPart(Combat.class);

        Health enemy1Health = enemy1.getPart(Health.class);
        Health enemy2Health = enemy2.getPart(Health.class);
        
        //Control test to check if enemies health value is set correct(3)
        assertEquals(3, enemy1Health.getLife());
        assertEquals(3, enemy2Health.getLife());
        
        //Attacks are performed directionally and enemy1 is above/north of player
        Movement playerMovement = player.getPart(Movement.class);
        playerMovement.setLastDirection('n');
        
        //Method for performing attack.
        AttackSystem attackSysem = new AttackSystem();
        attackSysem.performAttack(world);
        
        //enemy1 should take damage because it is within range
        assertEquals(3 - playerCombat.getAtkDmg(), enemy1Health.getLife());
        //enemy2 health should remain the same since it is out of range 
        assertEquals(3, enemy2Health.getLife());
        
        //Test to check number of entities in world. player + enemy1 + enemy2 = 3
        assertEquals(3, world.getEntities().size());
        
        //2 more attacks to kill enemy1
        attackSysem.performAttack(world);
        attackSysem.performAttack(world);
        
        //Enemy1 hits 0 health (dies) and gets removed from world
        assertEquals(0, enemy1Health.getLife());
        assertEquals(2, world.getEntities().size());
    }
}
