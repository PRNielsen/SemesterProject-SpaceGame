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
            
            
            
            movement.process(gameData, player);
            position.process(gameData, player);
            health.process(gameData, player);

        }
    }
    
    public void setAttackSystem(AttackSystemSPI attackSystem) {
        this.attackSystem = attackSystem;
    }

    public void removeAttackSystem(AttackSystemSPI attackSystem) {
        this.attackSystem = null;
    }    

}
