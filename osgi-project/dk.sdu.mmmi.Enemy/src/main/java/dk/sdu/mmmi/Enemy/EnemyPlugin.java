/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.Enemy;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.entitypart.Combat;
import dk.sdu.mmmi.common.data.entitypart.Health;
import dk.sdu.mmmi.common.data.entitypart.Movement;
import dk.sdu.mmmi.common.data.entitypart.Position;
import dk.sdu.mmmi.common.services.IGamePluginService;
import dk.sdu.mmmi.commonEnemy.Enemy;
import java.util.Random;

public class EnemyPlugin implements IGamePluginService {
    private String enemyID;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        Entity enemy = createEnemy(gameData);
        enemyID = world.addEntity(enemy);
//        enemyID = world.addEntity(enemy);
//        enemyID = world.addEntity(enemy);
        
    }

    private Entity createEnemy(GameData gameData) {
        Entity enemy = new Enemy();

        float deacceleration = 1000;
        float acceleration = 200;
        float maxSpeed = 250;
        float rotationSpeed = 5;
        float x = new Random().nextFloat() * gameData.getDisplayWidth();
        float y = new Random().nextFloat() * gameData.getDisplayHeight();
        float radians = 3.1415f / 2;
        
        int atkDmg = 1;
        int range = 50;
        
        enemy.add(new Health(3));
        enemy.setRadius(4);
        enemy.add(new Movement(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemy.add(new Position(x, y, radians));
        enemy.add(new Combat(atkDmg, range));
        
        return enemy;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemyID);
    }
}
