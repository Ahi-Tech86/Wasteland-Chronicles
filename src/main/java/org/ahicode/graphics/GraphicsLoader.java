package org.ahicode.graphics;

import org.ahicode.entity.enums.Direction;
import org.ahicode.entity.enums.Action;
import org.ahicode.graphics.animation.AnimationKey;
import org.ahicode.utility.SpriteManager;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class GraphicsLoader {
    private static final String characterBase = "/character/PlayerBase.png";
    private static final String characterHands = "/character/PlayerHands.png";
    private static final String shotgunHands = "/character/Shotgun.png";

    public static Map<AnimationKey, BufferedImage[]> loadPlayerBodyAnimations() {
        Map<AnimationKey, BufferedImage[]> animations = new HashMap<>();

        BufferedImage[] idleLeftFrames = SpriteManager.loadFramesFromSheet(
                characterBase,
                16,
                16,
                0,
                0,
                3,
                3
        );

        BufferedImage[] idleRightFrames = SpriteManager.loadFramesFromSheet(
                characterBase,
                16,
                16,
                1,
                0,
                3,
                3
        );

        BufferedImage[] idleUpFrames = SpriteManager.loadFramesFromSheet(
                characterBase,
                16,
                16,
                2,
                0,
                3,
                3
        );

        BufferedImage[] idleDownFrames = SpriteManager.loadFramesFromSheet(
                characterBase,
                16,
                16,
                3,
                0,
                3,
                3
        );

        BufferedImage[] runLeftFrames = SpriteManager.loadFramesFromSheet(
                characterBase,
                16,
                16,
                4,
                0,
                4,
                4
        );

        BufferedImage[] runRightFrames = SpriteManager.loadFramesFromSheet(
                characterBase,
                16,
                16,
                5,
                0,
                4,
                4
        );

        BufferedImage[] runUpFrames = SpriteManager.loadFramesFromSheet(
                characterBase,
                16,
                16,
                6,
                0,
                4,
                4
        );

        BufferedImage[] runDownFrames = SpriteManager.loadFramesFromSheet(
                characterBase,
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

        BufferedImage[] idleLeftFrames = SpriteManager.loadFramesFromSheet(
                characterHands,
                16,
                16,
                0,
                0,
                3,
                3
        );

        BufferedImage[] idleRightFrames = SpriteManager.loadFramesFromSheet(
                characterHands,
                16,
                16,
                1,
                0,
                3,
                3
        );

        BufferedImage[] idleUpFrames = SpriteManager.loadFramesFromSheet(
                characterHands,
                16,
                16,
                2,
                0,
                3,
                3
        );

        BufferedImage[] idleDownFrames = SpriteManager.loadFramesFromSheet(
                characterHands,
                16,
                16,
                3,
                0,
                3,
                3
        );

        BufferedImage[] runLeftFrames = SpriteManager.loadFramesFromSheet(
                characterHands,
                16,
                16,
                4,
                0,
                4,
                4
        );

        BufferedImage[] runRightFrames = SpriteManager.loadFramesFromSheet(
                characterHands,
                16,
                16,
                5,
                0,
                4,
                4
        );

        BufferedImage[] runUpFrames = SpriteManager.loadFramesFromSheet(
                characterHands,
                16,
                16,
                6,
                0,
                4,
                4
        );

        BufferedImage[] runDownFrames = SpriteManager.loadFramesFromSheet(
                characterHands,
                16,
                16,
                7,
                0,
                4,
                4
        );

        BufferedImage[] shotgunHoldFrames = new BufferedImage[1];
        shotgunHoldFrames[0] = SpriteManager.extractFromSheet(shotgunHands, 144, 16, 32, 16);

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
