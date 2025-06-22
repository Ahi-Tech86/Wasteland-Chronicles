package org.ahicode.utility;

import org.ahicode.entities.EntityState;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class AnimationLoader {

    public static Map<EntityState, BufferedImage[]> loadPlayerAnimations() {
        Map<EntityState, BufferedImage[]> animations = new HashMap<>();

        BufferedImage[] idleFrames = SpriteManager.loadFramesFromSpriteSheet(
                "/character/PlayerBase.png",
                16,
                16,
                0,
                0,
                3,
                3
        );

        BufferedImage[] runFrames = SpriteManager.loadFramesFromSpriteSheet(
                "/character/PlayerBase.png",
                16,
                16,
                5,
                0,
                3,
                3
        );

        animations.put(EntityState.IDLE, idleFrames);
        animations.put(EntityState.RUNNING, runFrames);

        return animations;
    }
}
