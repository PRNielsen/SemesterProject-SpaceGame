package dk.sdu.mmmi.attack;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.entitypart.Combat;
import dk.sdu.mmmi.common.data.entitypart.Health;
import dk.sdu.mmmi.common.data.entitypart.Position;
import dk.sdu.mmmi.common.services.IPostEntityProcessingService;

public class AttackDetector implements IPostEntityProcessingService {
     Combat attackerStats;
    
    
    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                
                // if the two entities are identical, skip the iteration
                if (entity1.getID().equals(entity2.getID())) {
                    continue;

                    // remove entities with zero in expiration
                }

                // Attack Detection
                if (this.withinAttackRange(entity1, entity2)) {
                    Health entityHealth = entity2.getPart(Health.class);
                    // if entity has been hit, and should have its life reduced
                    if (entityHealth.getLife() > 0) {
                        attackerStats = entity1.getPart(Combat.class);
                        entityHealth.setLife(entityHealth.getLife() - attackerStats.getAtkDmg());
                        entityHealth.setIsHit(true);
                        // if entity is out of life - remove
                        if (entityHealth.getLife() <= 0) {
                            world.removeEntity(entity1);
                        }
                    }
                }
            }
        }
    }

    public Boolean withinAttackRange(Entity attacker, Entity victim) {
        Position attackerPos = attacker.getPart(Position.class);
        Position victimPos = victim.getPart(Position.class);
        attackerStats = attacker.getPart(Combat.class);
        float dx = (float) attackerPos.getX() - (float) victimPos.getX();
        float dy = (float) attackerPos.getY() - (float) victimPos.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        if (distance < (attackerStats.getRange() + victim.getRadius())) {
            return true;
        }
        return false;
    }

}
