package dk.sdu.mmmi.common.data.MovingPart;

private float dx, dy;
private float deceleration, acceleration;
private float maxSpeed;
private boolean left, right, up, down;

public MovementPart(float deceleration, float acceleration, float maxSpeed) {
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

public  void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

public void setSpeed(float speed) {
        this.acceleration = speed;
        this.maxSpeed = speed;
    }

public void setLeft(boolean left) {
        this.left = left;

    }

public void setRight(boolean right) {
        this.left = left;

    }

public void setUp(boolean up) {
        this.up = up;

    }

public void setDown(boolean down) {
        this.down = down;

    }

@Override
public void process(GameData gameData, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float dt = gameData.getDelta();

        while (up) {
        y = positionPart.getY() + maxSpeed;
        }

        while (left) {
        x = positionPart.getX() - maxSpeed;
        }

        while (down) {
        y = positionPart.getY() - maxSpeed;
        }

        while (right){
        x = positionPart.getX() + maxSpeed;
        }
    }
}