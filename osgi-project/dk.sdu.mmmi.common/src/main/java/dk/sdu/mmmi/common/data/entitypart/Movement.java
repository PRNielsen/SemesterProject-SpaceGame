/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.common.data.entitypart;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import static java.lang.Math.sqrt;

/**
 *
 * @author Alexander
 */
public class Movement implements EntityPart {

    private float dx, dy;
    private float deceleration, acceleration;
    private float maxSpeed;
    private boolean left, right, up, down, space;
    private char lastDirection = 'n';

    public Movement(float deceleration, float acceleration, float maxSpeed, float rotationSpeed) {
        this.deceleration = deceleration;
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDeceleration(float deceleration) {
        this.deceleration = deceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setSpeed(float speed) {
        this.acceleration = speed;
        this.maxSpeed = speed;
    }
    
    public void setCollision() {
        if (getLastDirection() == 'w') {
            dx = 240;
            this.setLeft(false);
            
        }
        if (getLastDirection() == 'e') {
            dx = -240;
            this.setRight(false);
        }
        if (getLastDirection() == 'n') {
            dy = -240;
            this.setUp(false);
        }
        if (getLastDirection() == 's') {
            dy = 240;
            this.setDown(false);
        }
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
    
    public void setDown(boolean down) {
        this.down = down;
    }
    
    public void setSpace(boolean space) {
        this.space = space;
    }
    
    public void setLastDirection(char direction){
        this.lastDirection = direction;
    }
    
    public char getLastDirection(){
        return this.lastDirection;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        Position positionPart = entity.getPart(Position.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float dt = gameData.getDelta();

//        float radians = positionPart.getRadians();
        

        if (left) {
            if (dx > 0) {
                dx = 0;
                dy /= 2;
            }
            this.dx -=  acceleration * dt;
            setLastDirection('w');
//            radians = 3.1f;
        }

        if (right) {
            if (dx < 0) {
                dx = 0;
                dy /= 2;
            }
            this.dx += acceleration * dt;
            setLastDirection('e');
//            radians = 0;
        }
             
        if (up) {
            if (dy < 0) {
                dy = 0;
                dx /= 2;
            }
            dy +=  acceleration * dt;
            setLastDirection('n');
//            radians = 1.57f;
        }
        
        if (down) {
            if (dy > 0) {
                dy = 0;
                dx /= 2;
            }
            dy -=  acceleration * dt;
            setLastDirection('s');
//            radians = 4.7f;
        }
        
        if (space) {
            this.dx = dx + acceleration * dt;
        }
        
        // deccelerating
        float vec = (float) sqrt(this.dx * this.dx + dy * dy);
        if (vec > 0) {
           if(!(left || right || up || down || space)){
            this.dx -= (this.dx / vec) * deceleration * dt;
            dy -= (dy / vec) * deceleration * dt;
            if(vec <20){
                this.dx = 0;
                dy = 0;
            }
            }
        }
        if (vec > maxSpeed) {
            this.dx = (this.dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }

        // set position
        x += dx * dt;

        y += dy * dt;


        positionPart.setX(x);
        positionPart.setY(y);

    }
}
