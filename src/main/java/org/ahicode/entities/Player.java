package org.ahicode.entities;

import org.ahicode.animations.PlayerAnimations;

import java.awt.*;

import static org.ahicode.entities.Action.*;
import static org.ahicode.entities.Direction.*;

public class Player extends GameEntity {

    private final PlayerAnimations animations;
    private boolean hasWeapon;

    public Player(int x, int y, int screenWidth, int screenHeight, int tileSize) {
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

    public void render(Graphics2D graphics2D, int screenX, int screenY) {
        animations.render(graphics2D, screenX, screenY, 64, 64);
    }

    private void setAnimation() {
        Action bodyAction = this.isMoving() ? RUNNING : IDLE;
        Action handsAction = hasWeapon ? SHOTGUN_HOLD : (this.isMoving() ? RUNNING : IDLE);
        animations.setAction(bodyAction, handsAction, currentDirection);
    }

    private void updatePosition() {
        setMoving(false);

        if (isLeft() && !isRight()) {
            setWorldX(getWorldX() - speed);
            setMoving(true);
            currentDirection = LEFT;
        } else if (isRight() && !isLeft()) {
            setWorldX(getWorldX() + speed);
            setMoving(true);
            currentDirection = RIGHT;
        }

        if (isUp() && !isDown()) {
            setWorldY(getWorldY() - speed);
            setMoving(true);
            currentDirection = UP;
        } else if (isDown() && !isUp()) {
            setWorldY(getWorldY() + speed);
            setMoving(true);
            currentDirection = DOWN;
        }
    }

    public void setHasWeapon(boolean hasWeapon) {
        this.hasWeapon = hasWeapon;
    }
}
