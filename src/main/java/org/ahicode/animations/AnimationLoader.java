package org.ahicode.animations;

import org.ahicode.base.Direction;
import org.ahicode.entities.Action;
import org.ahicode.utility.SpriteManager;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class AnimationLoader {

    public static Map<AnimationKey, BufferedImage[]> loadPlayerAnimations() {
        Map<AnimationKey, BufferedImage[]> animations = new HashMap<>();
        String characterBaseFramesPath = "/character/PlayerBase.png";

        BufferedImage[] idleLeftFrames = SpriteManager.loadFramesFromSpriteSheet(
                characterBaseFramesPath,
                16,
                16,
                0,
                0,
                3,
                3
        );

        BufferedImage[] idleRightFrames = SpriteManager.loadFramesFromSpriteSheet(
                characterBaseFramesPath,
                16,
                16,
                1,
                0,
                3,
                3
        );

        BufferedImage[] idleUpFrames = SpriteManager.loadFramesFromSpriteSheet(
                characterBaseFramesPath,
                16,
                16,
                2,
                0,
                3,
                3
        );

        BufferedImage[] idleDownFrames = SpriteManager.loadFramesFromSpriteSheet(
                characterBaseFramesPath,
                16,
                16,
                3,
                0,
                3,
                3
        );

        BufferedImage[] runLeftFrames = SpriteManager.loadFramesFromSpriteSheet(
                "/character/PlayerBase.png",
                16,
                16,
                4,
                0,
                4,
                4
        );

        BufferedImage[] runRightFrames = SpriteManager.loadFramesFromSpriteSheet(
                "/character/PlayerBase.png",
                16,
                16,
                5,
                0,
                4,
                4
        );

        BufferedImage[] runUpFrames = SpriteManager.loadFramesFromSpriteSheet(
                "/character/PlayerBase.png",
                16,
                16,
                6,
                0,
                4,
                4
        );

        BufferedImage[] runDownFrames = SpriteManager.loadFramesFromSpriteSheet(
                "/character/PlayerBase.png",
                16,
                16,
                7,
                0,
                4,
                4
        );

        animations.put(new AnimationKey(Action.IDLE, Direction.LEFT), idleLeftFrames);
        animations.put(new AnimationKey(Action.IDLE, Direction.RIGHT), idleRightFrames);
        animations.put(new AnimationKey(Action.IDLE, Direction.UP), idleUpFrames);
        animations.put(new AnimationKey(Action.IDLE, Direction.DOWN), idleDownFrames);

        animations.put(new AnimationKey(Action.RUNNING, Direction.LEFT), runLeftFrames);
        animations.put(new AnimationKey(Action.RUNNING, Direction.RIGHT), runRightFrames);
        animations.put(new AnimationKey(Action.RUNNING, Direction.UP), runUpFrames);
        animations.put(new AnimationKey(Action.RUNNING, Direction.DOWN), runDownFrames);

        return animations;
    }
}
