/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.common.data.entitypart;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import static dk.sdu.mmmi.common.data.GameKeys.LEFT;
import static dk.sdu.mmmi.common.data.GameKeys.RIGHT;
import static dk.sdu.mmmi.common.data.GameKeys.UP;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 *
 * @author Alexander
 */
public class Movement
        implements EntityPart {

    private float dx, dy;
    private float deceleration, acceleration;
    private float maxSpeed, rotationSpeed;
    private boolean left, right, up, down, space;

    public Movement(float deceleration, float acceleration, float maxSpeed, float rotationSpeed) {
        this.deceleration = deceleration;
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
        this.rotationSpeed = rotationSpeed;
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

    public void setRotationSpeed(float rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
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

    @Override
    public void process(GameData gameData, Entity entity) {
        Position positionPart = entity.getPart(Position.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        float dt = gameData.getDelta();

        if (left) {
            dx -= acceleration * dt;
            
        }

        if (right) {
            dx += acceleration * dt;
            
        }
           
        if (up) {
            //dx += cos(radians) * acceleration * dt;
            //dy += sin(radians) * acceleration * dt;
            dy += acceleration * dt;
            //dx += acceleration * dt;
        }
        
        if (down) {
            dy -= acceleration * dt;
            //dx -= acceleration * dt;
        }
        
        if (space) {
            //dy = 0;
            //dx = 0;
            dx += acceleration * dt;
        }

        // deccelerating
        float vec = (float) sqrt(dx * dx + dy * dy);
        if (vec > 0) {
           if(!(left || right || up || down)){
            dx -= (dx / vec) * deceleration * dt;
            dy -= (dy / vec) * deceleration * dt;
            if(vec <20){
                dx = 0;
                dy = 0;
            }
            }
        }
        if (vec > maxSpeed) {
            dx = (dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }

        // set position
        x += dx * dt;
        if (x > gameData.getDisplayWidth()) {
            x = 0;
        } else if (x < 0) {
            x = gameData.getDisplayWidth();
        }

        y += dy * dt;
        if (y > gameData.getDisplayHeight()) {
            y = 0;
        } else if (y < 0) {
            y = gameData.getDisplayHeight();
        }

        positionPart.setX(x);
        positionPart.setY(y);

        positionPart.setRadians(radians);
    }

}