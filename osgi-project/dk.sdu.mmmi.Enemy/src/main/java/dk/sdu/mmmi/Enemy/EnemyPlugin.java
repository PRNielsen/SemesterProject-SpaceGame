/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.Enemy;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.entitypart.Asset;
import dk.sdu.mmmi.common.data.entitypart.Health;
import dk.sdu.mmmi.common.data.entitypart.Movement;
import dk.sdu.mmmi.common.data.entitypart.Position;
import dk.sdu.mmmi.common.services.IGamePluginService;
import dk.sdu.mmmi.commonEnemy.Enemy;
import java.util.Random;

public class EnemyPlugin implements IGamePluginService {
    Entity enemy1;
    Entity enemy2;
    Entity enemy3;
    Random rand = new Random();

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        enemy1 = createEnemy();
        enemy2 = createEnemy();
        enemy3 = createEnemy();
        world.addEntity(enemy1);
        world.addEntity(enemy2);
        world.addEntity(enemy3);
        
    }

    private Entity createEnemy() {
        Entity enemy = new Enemy();
        String assetString = "assets/texture.png";
        String jarName = "dk.sdu.mmmi.Enemy" + "-1.0-SNAPSHOT.jar!";
        String identifier = "dk.sdu.mmmi.Enemy";

        float deacceleration = 1000;
        float acceleration = 170;
        float maxSpeed = 250;
        float x = randomPos();
        float y = randomPos();
        float radians = 3.1415f / 2;
        
        enemy.add(new Health(3));
        enemy.setRadius(4);
        enemy.add(new Movement(deacceleration, acceleration, maxSpeed));
        enemy.add(new Position(x, y, radians));
        enemy.add(new Asset(assetString, jarName, identifier));
        
        return enemy;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy1);
        world.removeEntity(enemy2);
        world.removeEntity(enemy3);
    }    
    
    public int randomPos() {
        int randInt = (int) rand.nextInt(800);
        if (randInt < 200) {
            randInt += 400;
        } else return randInt; 
       return randInt;
    }
}
