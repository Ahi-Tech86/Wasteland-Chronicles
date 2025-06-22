package org.ahicode.entities;

import org.ahicode.utility.AnimationLoader;

import java.awt.*;

import static org.ahicode.entities.EntityState.*;

public class Player extends GameEntity {

    private final AnimationHandler animationHandler;

    public Player(int x, int y) {
        super(x, y);
        speed = 2;

        animationHandler = new AnimationHandler(this, AnimationLoader.loadPlayerAnimations(), IDLE, 25);
    }

    public void update() {
        updatePosition();
        animationHandler.update();
        setAnimation();
    }

    public void render(Graphics2D graphics2D) {
        animationHandler.render(graphics2D, getX(), getY(), 64, 64);
    }

    private void setAnimation() {
        EntityState action = this.isAttacking() ? ATTACK : (this.isMoving() ? RUNNING : IDLE);
        animationHandler.setAction(action);
    }

    private void updatePosition() {
        setMoving(false);

        if (isLeft() && !isRight()) {
            setX(getX() - speed);
            setMoving(true);
        } else if (isRight() && !isLeft()) {
            setX(getX() + speed);
            setMoving(true);
        }

        if (isUp() && !isDown()) {
            setY(getY() - speed);
            setMoving(true);
        } else if (isDown() && !isUp()) {
            setY(getY() + speed);
            setMoving(true);
        }
    }
}
