package dk.sdu.mmmi.dummyEnemy;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.entitypart.Movement;
import dk.sdu.mmmi.common.data.entitypart.Position;
//import dk.sdu.mmmi.common.enemy.Enemy;
import dk.sdu.mmmi.common.services.IEntityProcessingService;

public class EnemyProcessor implements IEntityProcessingService {


    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities(Enemy.class)) {

            Position positionPart = entity.getPart(Position.class);
            Movement movingPart = entity.getPart(Movement.class);
            double random = Math.random();
            //movingPart.setLeft(random < 0.2);
            movingPart.setLeft(false);
            //movingPart.setRight(random > 0.3 && random < 0.5);
            movingPart.setRight(false);
            //movingPart.setUp(random > 0.7 && random < 0.9);
            movingPart.setUp(false);
            
            
            movingPart.process(gameData, entity);
            positionPart.process(gameData, entity);            
            updateShape(entity);

        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        Position positionPart = entity.getPart(Position.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
