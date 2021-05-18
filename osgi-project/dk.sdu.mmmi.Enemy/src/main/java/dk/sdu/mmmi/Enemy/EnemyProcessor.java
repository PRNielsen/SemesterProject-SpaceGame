/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.Enemy;

import dk.sdu.mmmi.attack.AttackSystemSPI;
import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.entitypart.Movement;
import dk.sdu.mmmi.common.data.entitypart.Position;
import dk.sdu.mmmi.common.services.IEntityProcessingService;
import dk.sdu.mmmi.commonEnemy.Enemy;
import dk.sdu.mmmi.player.Player;
import java.util.Timer;

public class EnemyProcessor implements IEntityProcessingService {
    Entity enemy;
    float x;
    float y;
    
    boolean left;
    boolean right;
    boolean up;
    boolean down;
    
    private static AttackSystemSPI attackSystem;
    
    Timer timer = new Timer();
    int attackCoolDown = 5;


    @Override
    public void process(GameData gameData, World world) {

        for (Entity i : world.getEntities(Enemy.class)) {
            enemy = i;
            
            Position enemyPosition = enemy.getPart(Position.class);
            Movement enemyMovement = enemy.getPart(Movement.class);
            
//            int currentTime = 0;
//            if(!(attackSystem == null)){
//                while(currentTime <= attackCoolDown){
//                    currentTime++;
//                    if(attackCoolDown == 5){
//                        attackSystem.performAttack(world);
//                        System.out.println("Attack successful! ");
//                        attackCoolDown = 0;
//                    }
//                }  
//            }

            if (world.getEntities(Player.class).isEmpty() == false) {
                Entity player = world.getEntities(Player.class).get(0);
                Position targetPosition = player.getPart(Position.class);

                x = enemyPosition.getX();
                y = enemyPosition.getY();

                float playerX = targetPosition.getX();
                float playerY = targetPosition.getY();

                float dist = distanceCalculator(x, y, playerX, playerY);
                if (dist > 40) {
                    if (x > playerX) {
                        left = true;
                        right = false;
                    }
                    if (x < playerX) {
                        right = true;
                        left = false;
                    }
                    if (y > playerY) {
                        down = true;
                        up = false;
                    }
                    if (y < playerY) {
                        up = true;
                        down = false;
                    }
                }
            }
//            System.out.println(dist);
//            System.out.println("Left : " + left);
//            System.out.println("Right : " +right);
//            System.out.println("Up : " + up);
//            System.out.println("Down : " + down);
            
            enemyMovement.setLeft(left);
            enemyMovement.setRight(right);
            enemyMovement.setDown(down);
            enemyMovement.setUp(up);
            
            enemyMovement.process(gameData, enemy);
            enemyPosition.process(gameData, enemy);
            updateShape(enemy);

        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        Position positionPart = entity.getPart(Position.class);
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
    
    private float distanceCalculator(float currentX, float currentY, float targetX, float targetY){
        float distance = (float)Math.sqrt((targetX-currentX)*(targetX-currentX)+(targetY-currentY)*(targetY-currentY));
        return distance;
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