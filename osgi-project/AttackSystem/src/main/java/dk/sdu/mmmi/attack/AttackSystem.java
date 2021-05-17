package dk.sdu.mmmi.attack;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.entitypart.Combat;
import dk.sdu.mmmi.common.data.entitypart.Health;
import dk.sdu.mmmi.common.data.entitypart.Movement;
import dk.sdu.mmmi.common.data.entitypart.Position;
import dk.sdu.mmmi.common.services.IPostEntityProcessingService;

public class AttackSystem implements AttackSystemSPI {
     Combat attackerStats;
    
    
    @Override
    public void performAttack(World world) {
        // two loops for comparing two entities in the world
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                
                // if the two entities are identical, skip the iteration
                if (entity1.getID().equals(entity2.getID())) {
                    continue;

                }
                attackerStats = entity1.getPart(Combat.class); //gets combat entity part
                if(!(attackerStats == null)){ //Checks if entity1 have a Combat entitypart. No Attack detection if no combat entitypart.
                    if (this.withinAttackRange(entity1, entity2)) { //Attack Detection
                        
                        //if Attack detection is true, entity 2 should get hit and take damage
                        Health entityHealth = entity2.getPart(Health.class); //gets health entity part
                        if (entityHealth.getLife() > 0) {
                            entityHealth.setLife(entityHealth.getLife() - attackerStats.getAtkDmg()); //Minus entity2 health by entity1 attackDmg
                            entityHealth.setIsHit(true);
                            
                            // if entity is out of life - remove
                            if (entityHealth.getLife() <= 0) {
                                world.removeEntity(entity2);
                            }
                        }
                    }
                }
            }
        }
    }
    
    //Method for detecting if victim(entity2) is within attack range of attacker(entity1)
    public Boolean withinAttackRange(Entity attacker, Entity victim) {
        //Getting postion of attacker and victim
        Position attackerPos = attacker.getPart(Position.class);
        Position victimPos = victim.getPart(Position.class);
        
        //Calculation of the distance between attacker and victim
        float dx = (float) attackerPos.getX() - (float) victimPos.getX(); //calculating difference of x coordinate
        float dy = (float) attackerPos.getY() - (float) victimPos.getY(); //calculating difference of y coordinate
        float distance = (float) Math.sqrt(dx * dx + dy * dy); // calculating combined distance of x and y coordinate
        
        attackerStats = attacker.getPart(Combat.class);
        
        if (distance < (attackerStats.getRange() + victim.getRadius())) {
            
            Movement attackerMovement = attacker.getPart(Movement.class);
            
            switch(attackerMovement.getLastDirection()){
                case 'n':
                    if(victimPos.getY() >= attackerPos.getY()){
                        return true;
                    }
                    break;
                case 's':
                    if(victimPos.getY() <= attackerPos.getY()){
                        return true;
                    }
                    break;
                case 'w':
                    if(victimPos.getX() <= attackerPos.getX()){
                        return true;
                    }
                    break;
                case 'e':
                    if(victimPos.getX() >= attackerPos.getX()){
                        return true;
                    }
                    break;
                default:
                    return false; 
            
            }
        }
        return false;
    }

}
