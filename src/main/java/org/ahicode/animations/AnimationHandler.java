package org.ahicode.animations;

import org.ahicode.entities.GameEntity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class AnimationHandler {

    private final Map<AnimationKey, BufferedImage[]> fullAnimations;
    private BufferedImage[] currentAnimation;
    private final GameEntity gameEntity;
    private AnimationKey currentKey;
    private final int animSpeed;
    private int animTick;
    private int animIndex;

    public AnimationHandler(GameEntity entity, Map<AnimationKey, BufferedImage[]> fullAnimations, AnimationKey initialKey, int animSpeed) {
        this.gameEntity = entity;
        this.fullAnimations = fullAnimations;
        this.currentKey = initialKey;
        this.animSpeed = animSpeed;
        this.currentAnimation = fullAnimations.get(initialKey);
    }

    public void update() {
        animTick++;

        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;

            if (animIndex >= currentAnimation.length) {
                animIndex = 0;
                gameEntity.setAttacking(false);
            }
        }
    }

    public void render(Graphics graphics, float x, float y, int width, int height) {
        if (animIndex < currentAnimation.length) {
            graphics.drawImage(currentAnimation[animIndex], (int) x, (int) y, width, height, null);
        }
    }

    public void setAction(AnimationKey key) {
        if (currentKey.equals(key)) return;
        resetAnimTick();
        currentKey = key;
        currentAnimation = fullAnimations.get(key);
    }

    private void resetAnimTick() {
        animTick = 0;
        animIndex = 0;
    }
}
