package org.ahicode.entities;

import org.ahicode.animations.AnimationHandler;
import org.ahicode.animations.AnimationKey;
import org.ahicode.base.Direction;
import org.ahicode.animations.AnimationLoader;

import java.awt.*;

import static org.ahicode.entities.Action.*;

public class Player extends GameEntity {

    private final AnimationHandler animationHandler;

    public Player(int x, int y) {
        super(x, y);
        speed = 2;

        animationHandler = new AnimationHandler(this, AnimationLoader.loadPlayerAnimations(), new AnimationKey(IDLE, Direction.DOWN), 25);
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
        Action action = this.isAttacking() ? ATTACK : (this.isMoving() ? RUNNING : IDLE);
        AnimationKey key = new AnimationKey(action, currentDirection);
        animationHandler.setAction(key);
    }

    private void updatePosition() {
        setMoving(false);

        if (isLeft() && !isRight()) {
            setX(getX() - speed);
            setMoving(true);
            currentDirection = Direction.LEFT;
        } else if (isRight() && !isLeft()) {
            setX(getX() + speed);
            setMoving(true);
            currentDirection = Direction.RIGHT;
        }

        if (isUp() && !isDown()) {
            setY(getY() - speed);
            setMoving(true);
            currentDirection = Direction.UP;
        } else if (isDown() && !isUp()) {
            setY(getY() + speed);
            setMoving(true);
            currentDirection = Direction.DOWN;
        }
    }
}
