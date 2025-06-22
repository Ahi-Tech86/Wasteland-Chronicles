package org.ahicode.entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class AnimationHandler {

    private final Map<EntityState, BufferedImage[]> fullAnimations;
    private BufferedImage[] currentAnimation;
    private final GameEntity gameEntity;
    private EntityState currentAction;
    private final int animSpeed;
    private int animTick;
    private int animIndex;

    public AnimationHandler(GameEntity entity, Map<EntityState, BufferedImage[]> fullAnimations, EntityState initialAction, int animSpeed) {
        this.gameEntity = entity;
        this.fullAnimations = fullAnimations;
        this.currentAction = initialAction;
        this.animSpeed = animSpeed;
        this.currentAnimation = fullAnimations.get(initialAction);
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

    public void setAction(EntityState action) {
        if (currentAction == action) return;
        resetAnimTick();
        currentAction = action;
        currentAnimation = fullAnimations.get(action);
    }

    private void resetAnimTick() {
        animTick = 0;
        animIndex = 0;
    }
}
