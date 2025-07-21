package org.ahicode.graphics.animation;

import lombok.Getter;
import org.ahicode.entity.GameEntity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

import static org.ahicode.core.GameSettings.*;

public class AnimationController {

    private final Map<AnimationKey, BufferedImage[]> fullAnimations;
    private BufferedImage[] currentAnimation;
    private final GameEntity gameEntity;
    @Getter
    private AnimationKey currentKey;
    private final int animSpeed;
    private int animTick;
    private int animIndex;

    public AnimationController(GameEntity entity, Map<AnimationKey, BufferedImage[]> fullAnimations, AnimationKey initialKey, int animSpeed) {
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

    public void render(Graphics graphics, float x, float y) {
        int spriteHeight = currentAnimation[animIndex].getHeight();
        int spriteWidth = currentAnimation[animIndex].getWidth();

        if (animIndex < currentAnimation.length) {
            graphics.drawImage(currentAnimation[animIndex], (int) x, (int) y, spriteWidth * SCALE, spriteHeight * SCALE, null);
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
