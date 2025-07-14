package org.ahicode.entities;

import org.ahicode.core.WorldPositionedObject;
import org.ahicode.entities.enums.Direction;

import java.awt.*;

public abstract class GameEntity extends WorldPositionedObject {

    private boolean left, right, down, up;
    private Direction currentDirection;
    private boolean collisionOn = false;
    private boolean attacking = false;
    private boolean moving = false;
    private Rectangle hitbox;
    private int hitboxDefaultX;
    private int hitboxDefaultY;
    private int speed;

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

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public int getHitboxDefaultX() {
        return hitboxDefaultX;
    }

    public void setHitboxDefaultX(int hitboxDefaultX) {
        this.hitboxDefaultX = hitboxDefaultX;
    }

    public int getHitboxDefaultY() {
        return hitboxDefaultY;
    }

    public void setHitboxDefaultY(int hitboxDefaultY) {
        this.hitboxDefaultY = hitboxDefaultY;
    }
}
