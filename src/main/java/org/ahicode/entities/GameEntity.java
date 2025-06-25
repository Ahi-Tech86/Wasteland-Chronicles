package org.ahicode.entities;

import org.ahicode.core.GameObject2D;
import org.ahicode.entities.enums.Direction;

public abstract class GameEntity extends GameObject2D {

    private boolean left, right, down, up;
    protected Direction currentDirection;
    private boolean attacking = false;
    private boolean moving = false;
    protected int speed;

    public GameEntity(int worldX, int worldY) {
        super(worldX, worldY);
        currentDirection = Direction.DOWN;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
