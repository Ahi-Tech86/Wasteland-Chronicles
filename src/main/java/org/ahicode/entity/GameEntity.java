package org.ahicode.entity;

import org.ahicode.core.WorldPositionedObject;
import org.ahicode.entity.enums.Direction;
import org.ahicode.audio.Sound;

import java.awt.*;
import java.util.Random;

public abstract class GameEntity extends WorldPositionedObject {

    private final Sound soundEffects;
    private final Random random = new Random();
    private boolean left, right, down, up;
    private Direction currentDirection;
    private boolean collisionOn = false;
    private boolean attacking = false;
    private boolean moving = false;
    private Rectangle hitbox;
    private int hitboxDefaultX;
    private int hitboxDefaultY;
    private int speed;
    private boolean wasOnBush = false;

    public GameEntity(int worldX, int worldY, Sound soundEffects) {
        super(worldX, worldY);
        this.soundEffects = soundEffects;
        currentDirection = Direction.DOWN;
    }

    public void playBushSoundEffect() {
        if (isMoving()) {
            if (!wasOnBush) {
                String soundId = (random.nextInt(2) == 0) ? "bush1" : "bush2";
                soundEffects.play(soundId);
            }

            wasOnBush = true;
        } else {
            wasOnBush = false;
        }
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
