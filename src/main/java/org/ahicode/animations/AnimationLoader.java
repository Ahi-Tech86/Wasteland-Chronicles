package org.ahicode.animations;

import org.ahicode.entities.Direction;
import org.ahicode.entities.Action;
import org.ahicode.utility.SpriteManager;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class AnimationLoader {

    public static Map<AnimationKey, BufferedImage[]> loadPlayerBodyAnimations() {
        Map<AnimationKey, BufferedImage[]> animations = new HashMap<>();
        String characterBaseFramesPath = "/character/PlayerBase.png";

        BufferedImage[] idleLeftFrames = SpriteManager.loadFramesFromSheet(
                characterBaseFramesPath,
                16,
                16,
                0,
                0,
                3,
                3
        );

        BufferedImage[] idleRightFrames = SpriteManager.loadFramesFromSheet(
                characterBaseFramesPath,
                16,
                16,
                1,
                0,
                3,
                3
        );

        BufferedImage[] idleUpFrames = SpriteManager.loadFramesFromSheet(
                characterBaseFramesPath,
                16,
                16,
                2,
                0,
                3,
                3
        );

        BufferedImage[] idleDownFrames = SpriteManager.loadFramesFromSheet(
                characterBaseFramesPath,
                16,
                16,
                3,
                0,
                3,
                3
        );

        BufferedImage[] runLeftFrames = SpriteManager.loadFramesFromSheet(
                characterBaseFramesPath,
                16,
                16,
                4,
                0,
                4,
                4
        );

        BufferedImage[] runRightFrames = SpriteManager.loadFramesFromSheet(
                characterBaseFramesPath,
                16,
                16,
                5,
                0,
                4,
                4
        );

        BufferedImage[] runUpFrames = SpriteManager.loadFramesFromSheet(
                characterBaseFramesPath,
                16,
                16,
                6,
                0,
                4,
                4
        );

        BufferedImage[] runDownFrames = SpriteManager.loadFramesFromSheet(
                characterBaseFramesPath,
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

    public static Map<AnimationKey, BufferedImage[]> loadPlayerHandsAnimations() {
        Map<AnimationKey, BufferedImage[]> animations = new HashMap<>();
        String characterHandsFramesPath = "/character/PlayerHands.png";
        String shotgunHandsFramesPath = "/character/Shotgun.png";

        BufferedImage[] idleLeftFrames = SpriteManager.loadFramesFromSheet(
                characterHandsFramesPath,
                16,
                16,
                0,
                0,
                3,
                3
        );

        BufferedImage[] idleRightFrames = SpriteManager.loadFramesFromSheet(
                characterHandsFramesPath,
                16,
                16,
                1,
                0,
                3,
                3
        );

        BufferedImage[] idleUpFrames = SpriteManager.loadFramesFromSheet(
                characterHandsFramesPath,
                16,
                16,
                2,
                0,
                3,
                3
        );

        BufferedImage[] idleDownFrames = SpriteManager.loadFramesFromSheet(
                characterHandsFramesPath,
                16,
                16,
                3,
                0,
                3,
                3
        );

        BufferedImage[] runLeftFrames = SpriteManager.loadFramesFromSheet(
                characterHandsFramesPath,
                16,
                16,
                4,
                0,
                4,
                4
        );

        BufferedImage[] runRightFrames = SpriteManager.loadFramesFromSheet(
                characterHandsFramesPath,
                16,
                16,
                5,
                0,
                4,
                4
        );

        BufferedImage[] runUpFrames = SpriteManager.loadFramesFromSheet(
                characterHandsFramesPath,
                16,
                16,
                6,
                0,
                4,
                4
        );

        BufferedImage[] runDownFrames = SpriteManager.loadFramesFromSheet(
                characterHandsFramesPath,
                16,
                16,
                7,
                0,
                4,
                4
        );

        BufferedImage[] shotgunHoldFrames = new BufferedImage[1];
        shotgunHoldFrames[0] = SpriteManager.extractFromSheet(shotgunHandsFramesPath, 144, 16, 32, 16);

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
