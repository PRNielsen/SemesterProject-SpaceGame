/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.common.data.entitypart;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;

/**
 *
 * @author Alexander from: PaxAsteroids/OSGiCommon/dk/sdu/mmmi/cbse/common/data/entityparts/PositionPart
 * with enhancements.
 */
public class Position implements EntityPart {

    private float x;
    private float y;
    private float radians;

    public Position(float x, float y, float radians) {
        this.x = x;
        this.y = y;
        //this.radians = radians;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadians() {
        return radians;
    }

    public void setX(float newX) {
        this.x = newX;
    }

    public void setY(float newY) {
        this.y = newY;
    }

    public void setPosition(float newX, float newY) {
        this.x = newX;
        this.y = newY;
    }

    public void setRadians(float radians) {
        this.radians = radians;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
    }

}
