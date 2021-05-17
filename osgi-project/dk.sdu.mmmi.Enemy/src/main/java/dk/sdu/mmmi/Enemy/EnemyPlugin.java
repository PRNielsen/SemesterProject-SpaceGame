/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.Enemy;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.entitypart.Health;
import dk.sdu.mmmi.common.data.entitypart.Movement;
import dk.sdu.mmmi.common.data.entitypart.Position;
import dk.sdu.mmmi.common.services.IGamePluginService;
import dk.sdu.mmmi.commonEnemy.Enemy;

public class EnemyPlugin implements IGamePluginService {
    private String enemyID;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        Entity enemy = createEnemyShip(gameData);
        enemyID = world.addEntity(enemy);
        
    }

    private Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new Enemy();

        float deacceleration = 1000;
        float acceleration = 200;
        float maxSpeed = 250;
        float rotationSpeed = 5;
        float x = 5.5f;
        float y = 5.5f;
        float radians = 3.1415f / 2;
        
        enemyShip.add(new Health(3));
        enemyShip.setRadius(4);
        enemyShip.add(new Movement(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemyShip.add(new Position(x, y, radians));
        
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemyID);
    }
}
