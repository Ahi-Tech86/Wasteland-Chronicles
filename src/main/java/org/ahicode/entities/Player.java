package org.ahicode.entities;

import org.ahicode.animations.PlayerAnimations;

import java.awt.*;

import static org.ahicode.entities.Action.*;
import static org.ahicode.entities.Direction.*;

public class Player extends GameEntity {

    private final PlayerAnimations animations;
    private boolean hasWeapon;

    public Player(int x, int y) {
        super(x, y);
        speed = 2;
        hasWeapon = false;

        animations = new PlayerAnimations(this);
    }

    public void update() {
        updatePosition();
        animations.update();
        setAnimation();
    }

    public void render(Graphics2D graphics2D) {
        animations.render(graphics2D, getX(), getY(), 64, 64);
    }

    private void setAnimation() {
        Action bodyAction = this.isMoving() ? RUNNING : IDLE;
        Action handsAction = hasWeapon ? SHOTGUN_HOLD : (this.isMoving() ? RUNNING : IDLE);
        animations.setAction(bodyAction, handsAction, currentDirection);
    }

    private void updatePosition() {
        setMoving(false);

        if (isLeft() && !isRight()) {
            setX(getX() - speed);
            setMoving(true);
            currentDirection = LEFT;
        } else if (isRight() && !isLeft()) {
            setX(getX() + speed);
            setMoving(true);
            currentDirection = RIGHT;
        }

        if (isUp() && !isDown()) {
            setY(getY() - speed);
            setMoving(true);
            currentDirection = UP;
        } else if (isDown() && !isUp()) {
            setY(getY() + speed);
            setMoving(true);
            currentDirection = DOWN;
        }
    }

    public void setHasWeapon(boolean hasWeapon) {
        this.hasWeapon = hasWeapon;
    }
}
