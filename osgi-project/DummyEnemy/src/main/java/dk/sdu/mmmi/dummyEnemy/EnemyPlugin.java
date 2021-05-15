package dk.sdu.mmmi.dummyEnemy;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.entitypart.Asset;
import dk.sdu.mmmi.common.data.entitypart.Combat;
import dk.sdu.mmmi.common.data.entitypart.Health;
import dk.sdu.mmmi.common.data.entitypart.Movement;
import dk.sdu.mmmi.common.data.entitypart.Position;
//import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {
    private String enemyID;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        Entity enemy = createEnemy(gameData);
        enemyID = world.addEntity(enemy);
        
    }

    private Entity createEnemy(GameData gameData) {
        Entity enemy = new Enemy();
        String assetString = "assets/texture.png";
        String jarName = "DummyEnemy" + "-1.0-SNAPSHOT.jar!";
        String identifier = "DummyEnemy";

        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = 200;//gameData.getDisplayWidth() / 3;
        float y = 200;//gameData.getDisplayHeight() / 3;
        float radians = 3.1415f / 2;
        int atkDmg = 1;
        int range = 100;
        
        enemy.add(new Health(3));
        enemy.setRadius(4);
        enemy.add(new Movement(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemy.add(new Position(x, y, radians));
        enemy.add(new Asset(assetString, jarName, identifier));
        //enemyShip.add(new Combat(atkDmg, range));
        
        return enemy;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemyID);
    }

}
