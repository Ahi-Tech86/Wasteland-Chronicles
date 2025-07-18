package org.ahicode.entity;

import lombok.Getter;
import lombok.Setter;
import org.ahicode.core.WorldPositionedObject;
import org.ahicode.entity.enums.Direction;
import org.ahicode.audio.Sound;

import java.awt.*;
import java.util.Random;

@Getter
@Setter
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
}
