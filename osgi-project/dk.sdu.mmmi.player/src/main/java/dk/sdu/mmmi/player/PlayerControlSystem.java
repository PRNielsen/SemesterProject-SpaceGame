package dk.sdu.mmmi.player;

import dk.sdu.mmmi.attack.AttackSystemSPI;
import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.GameKeys;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.entitypart.Health;
import dk.sdu.mmmi.common.data.entitypart.Movement;
import dk.sdu.mmmi.common.data.entitypart.Position;
import dk.sdu.mmmi.common.services.IEntityProcessingService;

public class PlayerControlSystem implements IEntityProcessingService {
    
    private static AttackSystemSPI attackSystem;
    
    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            Position position = player.getPart(Position.class);
            Movement movement = player.getPart(Movement.class);
            Health health = player.getPart(Health.class);

            //Set keys to true if the key is pressed down.
            movement.setLeft(gameData.getKeys().isDown(GameKeys.LEFT));
            movement.setRight(gameData.getKeys().isDown(GameKeys.RIGHT));
            movement.setUp(gameData.getKeys().isDown(GameKeys.UP));
            movement.setDown(gameData.getKeys().isDown(GameKeys.DOWN));

            if(!(attackSystem == null)){
                if(gameData.getKeys().isDown(GameKeys.SPACE)){
                    attackSystem.performAttack(world);
                }
            }
            /*if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                Entity bullet = Lookup.getDefault().lookup(BulletSPI.class).createBullet(player, gameData);
                world.addEntity(bullet);
            }*/
            
            movement.process(gameData, player);
            position.process(gameData, player);
            health.process(gameData, player);

            updateShape(player);

        }
    }

    private void updateShape(Entity entity) {
        
        float[] shapex = new float[4];
        float[] shapey = new float[4];
        Position positionPart = entity.getPart(Position.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * entity.getRadius());
        shapey[0] = (float) (y + Math.sin(radians) * entity.getRadius());

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * entity.getRadius());
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * entity.getRadius());

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * entity.getRadius() * 0.5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * entity.getRadius() * 0.5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * entity.getRadius());
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * entity.getRadius());

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);

    }
    
    public void setAttackSystem(AttackSystemSPI attackSystem) {
        this.attackSystem = attackSystem;
        //attackSystemList.add(attackSystem);
    }

    public void removeAttackSystem(AttackSystemSPI attackSystem) {
        this.attackSystem = null;
        //attackSystemList.remove(attackSystem);
    }    

}
