package dk.sdu.mmmi.player;

import dk.sdu.mmmi.common.data.Entity;

public class Player<atk_dmg, health> extends Entity {

    int health = 10;
    int atk_dmg = 1;
    boolean weapon = false;

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    private int movementSpeed;
    private int accel;
    private int deAccel;

    public void player() {
        movementSpeed = 200;
        accel = 200;
        deAccel = 200;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setDown(boolean b) {
        down = b;
    }

    public void update(float dt) {
        //need to be able to draw a map before making
    }
}